package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileReader {

    protected abstract String getValueOfField(String fieldsLine)
            throws WrongFileFormatException;

    protected abstract int checkSpecificReceipt(String[] values);

    public void readFile(final String fileName)
            throws NumberFormatException, IOException,
            WrongTaxpayerStatusException, WrongFileFormatException,
            WrongReceiptKindException, WrongReceiptDateException {

        BufferedReader inputStream = new BufferedReader(new java.io.FileReader(fileName));
        String fullname = getValueOfField(inputStream.readLine());
        int taxRegistrationNumber = Integer.parseInt(getValueOfField(inputStream.readLine()));
        String status = getValueOfField(inputStream.readLine());
        float income = Float.parseFloat(getValueOfField(inputStream.readLine()));
        createTaxpayer(fullname, taxRegistrationNumber, income, status);
        while (readReceipt(inputStream, taxRegistrationNumber)) ;
    }

    protected boolean readReceipt(final BufferedReader inputStream,
                                  final int taxRegistrationNumber)
            throws WrongFileFormatException, IOException,
            WrongReceiptKindException, WrongReceiptDateException {

        int receiptId = checkForReceipt(inputStream);
        if (receiptId < 0) return false;

        String issueDate = getValueOfField(inputStream.readLine());
        String kind = getValueOfField(inputStream.readLine());
        float amount = Float.parseFloat(getValueOfField(inputStream.readLine()));
        String companyName = getValueOfField(inputStream.readLine());
        String country = getValueOfField(inputStream.readLine());
        String city = getValueOfField(inputStream.readLine());
        String street = getValueOfField(inputStream.readLine());
        int number = Integer.parseInt(getValueOfField(inputStream.readLine()));

        Company company = new Company(companyName, country, city, street, number);
        Receipt receipt = new Receipt(receiptId, issueDate, amount, kind, company);
        createReceipt(receipt, taxRegistrationNumber);

        return true;
    }

    protected void createTaxpayer(final String fullname,
                                  final int taxRegistrationNumber,
                                  final float income, final String status)
            throws WrongTaxpayerStatusException {

        TaxpayerManager manager = new TaxpayerManager();
        manager.createTaxpayer(fullname, taxRegistrationNumber, status, income);
    }

    protected void createReceipt(Receipt receipt, final int taxRegistrationNumber)
            throws WrongReceiptKindException {

        TaxpayerManager manager = new TaxpayerManager();
        manager.createReceipt(receipt, taxRegistrationNumber);
    }

    protected boolean isEmpty(final String line) {
        return line == null;
    }

    protected int checkForReceipt(final BufferedReader inputStream)
            throws NumberFormatException, IOException {
        String line = inputStream.readLine();
        while (!isEmpty(line)) {
            int receiptId = checkSpecificReceipt(line.split(" ", 3));
            if (receiptId != -1) return receiptId;

            line = inputStream.readLine();
        }
        return -1;
    }

}
