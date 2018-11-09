package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;

public class XMLInfoWriter extends InfoWriter {

    @Override
    protected PrintWriter geneFileSpecParts(final int taxRegNum)
            throws IOException {
        PrintWriter outputStream =
                new PrintWriter(taxRegNum + "_INFO.xml");
        outputStream.println("<Name> " + manager.getTaxpayerName(taxRegNum) + " </Name>");
        outputStream.println("<AFM> " + taxRegNum + " </AFM>");
        outputStream.println("<Status> " + manager.getTaxpayerStatus(taxRegNum) + " </Status>");
        outputStream.println("<Income> " + manager.getTaxpayerIncome(taxRegNum) + " </Income>\n");
        outputStream.println("<Receipts>\n");
        return outputStream;
    }

    @Override
    protected void genReceiptsSpecParts(final Receipt receipt,
                                        final PrintWriter outputStream) {
        outputStream.println("<ReceiptID> "
                + receipt.getId() + " </ReceiptID>");
        outputStream.println("<Date> " + receipt.getIssueDate() + " </Date>");
        outputStream.println("<Kind> " + receipt.getKind() + " </Kind>");
        outputStream.println("<Amount> " + receipt.getAmount() + " </Amount>");
        printCompany(receipt, outputStream);
    }

    private void printCompany(Receipt receipt, PrintWriter outputStream) {
        Company company = receipt.getCompany();
        outputStream.println("<Company> " + company.getName() + " </Company>");
        outputStream.println("<Country> "
                + company.getCountry() + " </Country>");
        outputStream.println("<City> " + company.getCity() + " </City>");
        outputStream.println("<Street> " + company.getStreet() + " </Street>");
        outputStream.println("<Number> " + company.getNumber() + " </Number>");
    }

}