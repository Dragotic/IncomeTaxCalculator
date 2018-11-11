package incometaxcalculator.data.management;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.io.*;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

    private static HashMap<Integer, Taxpayer> taxpayerHashMap =
            new HashMap<Integer, Taxpayer>(0);
    private static HashMap<Integer, Integer> receiptOwnerTRN =
            new HashMap<Integer, Integer>(0);

    public void createTaxpayer(final String fullname,
                               final int taxRegistrationNumber,
                               final String status, final float income)
            throws WrongTaxpayerStatusException {
        taxpayerHashMap.put(taxRegistrationNumber,
                TaxpayerFactory.createTaxpayer(status, fullname,
                                                taxRegistrationNumber, income));

    }

    public void createReceipt(final Receipt receipt,
                              final int taxRegistrationNumber)
            throws WrongReceiptKindException {

        taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
        receiptOwnerTRN.put(receipt.getId(), taxRegistrationNumber);

    }

    public void removeTaxpayer(final int taxRegistrationNumber) {
        Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
        taxpayerHashMap.remove(taxRegistrationNumber);
        HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
        Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry<Integer, Receipt> entry = iterator.next();
            Receipt receipt = entry.getValue();
            receiptOwnerTRN.remove(receipt.getId());
        }
    }

    public void addReceipt(final Receipt receipt,
                           final int taxRegistrationNumber)
            throws IOException, WrongReceiptKindException,
                    ReceiptAlreadyExistsException {

        if (containsReceipt(receipt.getId())) {
            throw new ReceiptAlreadyExistsException();
        }
        createReceipt(receipt, taxRegistrationNumber);
        updateFiles(taxRegistrationNumber);
    }

    public void removeReceipt(final int receiptId)
            throws IOException, WrongReceiptKindException {
        taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
        updateFiles(receiptOwnerTRN.get(receiptId));
        receiptOwnerTRN.remove(receiptId);
    }

    private void updateFiles(final int taxRegistrationNumber)
            throws IOException {
//        InfoWriter writer = InfoWriterFactory.createInfoWriter(taxRegistrationNumber);
//        writer.generateFile(taxRegistrationNumber);
        // TODO: Ask professor the logic behind this method
        if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
            new XMLInfoWriter().generateFile(taxRegistrationNumber);
        } else {
            new TXTInfoWriter().generateFile(taxRegistrationNumber);
            return;
        }
        if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
            new TXTInfoWriter().generateFile(taxRegistrationNumber);
        }
    }

    public void saveLogFile(final int taxRegistrationNumber,
                            final String fileFormat)
            throws IOException, WrongFileFormatException {
        LogWriter writer = LogWriterFactory.createLogWriter(fileFormat);
        writer.generateFile(taxRegistrationNumber);
    }

    public boolean containsTaxpayer(final int taxRegistrationNumber) {
        if (taxpayerHashMap.containsKey(taxRegistrationNumber)) {
            return true;
        }
        return false;
    }

    public boolean containsTaxpayer() {
        if (taxpayerHashMap.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean containsReceipt(final int id) {
        if (receiptOwnerTRN.containsKey(id)) {
            return true;
        }
        return false;

    }

    public void loadTaxpayer(final String fileName)
            throws NumberFormatException, IOException,
                    WrongFileFormatException, WrongFileEndingException,
                    WrongTaxpayerStatusException, WrongReceiptKindException,
                    WrongReceiptDateException {

        String[] ending = fileName.split("\\.");

        FileReader reader = FileReaderFactory.createFileReader(ending[1]);
        reader.readFile(fileName);

    }

    public String getTaxpayerName(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
    }

    public String getTaxpayerStatus(final int taxRegistrationNumber) {
        if (taxpayerHashMap.get(taxRegistrationNumber)
                instanceof MarriedFilingJointlyTaxpayer) {
            return "Married Filing Jointly";
        } else if (taxpayerHashMap
                .get(taxRegistrationNumber) instanceof MarriedFilingSeparatelyTaxpayer) {
            return "Married Filing Separately";
        } else if (taxpayerHashMap
                .get(taxRegistrationNumber) instanceof SingleTaxpayer) {
            return "Single";
        } else {
            return "Head of Household";
        }
    }

    public String getTaxpayerIncome(final int taxRegistrationNumber) {
        return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
    }

    public double getTaxpayerVarTaxOnRec(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
    }

    public int getTaxpayerTotalRecGathered(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
    }

    public float getTaxpayerAmountOfRecKind(final int taxRegistrationNumber,
                                            final short kind) {
        return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
    }

    public double getTaxpayerTotalTax(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
    }

    public double getTaxpayerBasicTax(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
    }

    public HashMap<Integer, Receipt> getReceiptHashMap(final int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
    }

}
