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

    private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
    private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);

    public void createTaxpayer(String fullname, int taxRegistrationNumber, String status,
                               float income) throws WrongTaxpayerStatusException {
        taxpayerHashMap.put(taxRegistrationNumber,
                TaxpayerFactory.createTaxpayer(status, fullname, taxRegistrationNumber, income));

    }

    public void createReceipt(Receipt receipt, int taxRegistrationNumber)
            throws WrongReceiptKindException {

        taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
        receiptOwnerTRN.put(receipt.getId(), taxRegistrationNumber);

    }

    public void removeTaxpayer(int taxRegistrationNumber) {
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

    public void addReceipt(Receipt receipt, int taxRegistrationNumber)
            throws IOException, WrongReceiptKindException, ReceiptAlreadyExistsException {

        if (containsReceipt(receipt.getId())) {
            throw new ReceiptAlreadyExistsException();
        }
        createReceipt(receipt, taxRegistrationNumber);
        updateFiles(taxRegistrationNumber);
    }

    public void removeReceipt(int receiptId) throws IOException, WrongReceiptKindException {
        taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
        updateFiles(receiptOwnerTRN.get(receiptId));
        receiptOwnerTRN.remove(receiptId);
    }

    private void updateFiles(int taxRegistrationNumber) throws IOException {
//        InfoWriter writer = InfoWriterFactory.createInfoWriter(taxRegistrationNumber);
//        writer.generateFile(taxRegistrationNumber);
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

    public void saveLogFile(int taxRegistrationNumber, String fileFormat)
            throws IOException, WrongFileFormatException {
        LogWriter writer = LogWriterFactory.createLogWriter(fileFormat);
        writer.generateFile(taxRegistrationNumber);
    }

    public boolean containsTaxpayer(int taxRegistrationNumber) {
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

    public boolean containsReceipt(int id) {
        if (receiptOwnerTRN.containsKey(id)) {
            return true;
        }
        return false;

    }

    public Taxpayer getTaxpayer(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber);
    }

    public void loadTaxpayer(String fileName)
            throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
            WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {

        String ending[] = fileName.split("\\.");

        FileReader reader = FileReaderFactory.createFileReader(ending[1]);
        reader.readFile(fileName);

    }

    public String getTaxpayerName(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
    }

    public String getTaxpayerStatus(int taxRegistrationNumber) {
        if (taxpayerHashMap.get(taxRegistrationNumber) instanceof MarriedFilingJointlyTaxpayer) {
            return "Married Filing Jointly";
        } else if (taxpayerHashMap
                .get(taxRegistrationNumber) instanceof MarriedFilingSeparatelyTaxpayer) {
            return "Married Filing Separately";
        } else if (taxpayerHashMap.get(taxRegistrationNumber) instanceof SingleTaxpayer) {
            return "Single";
        } else {
            return "Head of Household";
        }
    }

    public String getTaxpayerIncome(int taxRegistrationNumber) {
        return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
    }

    public double getTaxpayerVarTaxOnRec(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
    }

    public int getTaxpayerTotalRecGathered(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
    }

    public float getTaxpayerAmountOfRecKind(int taxRegistrationNumber, short kind) {
        return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
    }

    public double getTaxpayerTotalTax(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
    }

    public double getTaxpayerBasicTax(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
    }

    public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
        return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
    }

}