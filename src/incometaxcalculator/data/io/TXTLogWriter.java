package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogWriter extends LogWriter {

    @Override
    protected PrintWriter generateFileSpecificParts(int taxRegistrationNumber) throws IOException {
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
        outputStream.println("Name: " + manager.getTaxpayerName(taxRegistrationNumber));
        outputStream.println("AFM: " + taxRegistrationNumber);
        outputStream.println("Income: " + manager.getTaxpayerIncome(taxRegistrationNumber));
        outputStream.println("Basic Tax: " + manager.getTaxpayerBasicTax(taxRegistrationNumber));
        if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
            outputStream
                  .println("Tax Increase: " + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
        } else {
            outputStream
                  .println("Tax Decrease: " + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
        }
        outputStream.println("Total Tax: " + manager.getTaxpayerTotalTax(taxRegistrationNumber));
        outputStream.println(
                "TotalReceiptsGathered: " + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber));
        outputStream.println(
                "Entertainment: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT));
        outputStream.println("Basic: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC));
        outputStream
                .println("Travel: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL));
        outputStream
                .println("Health: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH));
        outputStream.println("Other: " + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER));
        return outputStream;
    }
}
