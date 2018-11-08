package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogWriter extends LogWriter {

    @Override
    protected PrintWriter genFileSpecParts(final int taxRegistrationNumber)
            throws IOException {
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
        outputStream.println("Name: "
                + manager.getTaxpayerName(taxRegistrationNumber));
        outputStream.println("AFM: " + taxRegistrationNumber);
        outputStream.println("Income: "
                + manager.getTaxpayerIncome(taxRegistrationNumber));
        outputStream.println("Basic Tax: "
                + manager.getTaxpayerBasicTax(taxRegistrationNumber));
        if (manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber)
                > 0) {
            outputStream
                    .println("Tax Increase: "
                       + manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber));
        } else {
            outputStream
                    .println("Tax Decrease: "
                       + manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber));
        }
        outputStream.println("Total Tax: "
                + manager.getTaxpayerTotalTax(taxRegistrationNumber));
        outputStream.println(
                "TotalReceiptsGathered: "
                  + manager.getTaxpayerTotalRecGathered(taxRegistrationNumber));
        outputStream.println(
                "Entertainment: "
                  + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                       ENTERTAINMENT));
        outputStream.println("Basic: "
                  + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                       BASIC));
        outputStream
                .println("Travel: "
                  + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                       TRAVEL));
        outputStream
                .println("Health: "
                  + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                       HEALTH));
        outputStream.println("Other: "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     OTHER));
        return outputStream;
    }
}
