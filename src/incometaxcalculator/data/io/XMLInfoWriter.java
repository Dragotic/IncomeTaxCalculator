package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {

    @Override
    protected PrintWriter generateFileSpecificParts(int taxRegistrationNumber) throws IOException {
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
        outputStream.println("<Name> " + manager.getTaxpayerName(taxRegistrationNumber) + " </Name>");
        outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
        outputStream.println("<Status> " + manager.getTaxpayerStatus(taxRegistrationNumber) + " </Status>");
        outputStream.println("<Income> " + manager.getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
        outputStream.println();// den mas emfanize to \n se aplo notepad
        outputStream.println("<Receipts>");
        outputStream.println();
        return outputStream;
    }

    @Override
    protected void generateReceiptsSpecificParts(Receipt receipt, PrintWriter outputStream) {
        outputStream.println("<ReceiptID> " + receipt.getId() + " </ReceiptID>");
        outputStream.println("<Date> " + receipt.getIssueDate() + " </Date>");
        outputStream.println("<Kind> " + receipt.getKind() + " </Kind>");
        outputStream.println("<Amount> " + receipt.getAmount() + " </Amount>");
        outputStream.println("<Company> " + receipt.getCompany() + " </Company>");
        outputStream.println("<Country> " + receipt.getCompany().getCountry() + " </Country>");
        outputStream.println("<City> " + receipt.getCompany().getCity() + " </City>");
        outputStream.println("<Street> " + receipt.getCompany().getStreet() + " </Street>");
        outputStream.println("<Number> " + receipt.getCompany().getNumber() + " </Number>");
    }

}