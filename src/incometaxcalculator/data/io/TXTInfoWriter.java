package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;

public class TXTInfoWriter extends InfoWriter implements FileWriter {

    @Override
    protected PrintWriter geneFileSpecParts(int taxRegNum)
            throws IOException {
        PrintWriter outputStream = new PrintWriter(taxRegNum + "_INFO.txt");
        outputStream.println("Name: " + manager.getTaxpayerName(taxRegNum));
        outputStream.println("AFM: " + taxRegNum);
        outputStream.println("Status: " + manager.getTaxpayerStatus(taxRegNum));
        outputStream.println("Income: "
                + manager.getTaxpayerIncome(taxRegNum) + "\n");
        outputStream.println("Receipts:\n");
        return outputStream; }

    @Override
    protected void genReceiptsSpecParts(final Receipt receipt,
                                        final PrintWriter outputStream) {
        outputStream.println("Receipt ID: " + receipt.getId());
        outputStream.println("Date: " + receipt.getIssueDate());
        outputStream.println("Kind: " + receipt.getKind());
        outputStream.println("Amount: " + receipt.getAmount());
        printCompany(receipt, outputStream);
    }

    private void printCompany(Receipt receipt, PrintWriter outputStream) {
        Company company = receipt.getCompany();
        outputStream.println("Company: " + company.getName());
        outputStream.println("Country: " + company.getCountry());
        outputStream.println("City: " + company.getCity());
        outputStream.println("Street: " + company.getStreet());
        outputStream.println("Number: " + company.getNumber());
    }

}
