package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLLogWriter extends LogWriter {

    @Override
    protected PrintWriter genFileSpecParts(final int taxRegistrationNumber)
            throws IOException {
        PrintWriter outputStream = new PrintWriter(
                new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
        outputStream.println("<Name> "
                + manager.getTaxpayerName(taxRegistrationNumber)
                + " </Name>");
        outputStream.println("<AFM> "
                + taxRegistrationNumber
                + " </AFM>");
        outputStream.println("<Income> "
                + manager.getTaxpayerIncome(taxRegistrationNumber)
                + " </Income>");
        outputStream.println("<BasicTax> "
                + manager.getTaxpayerBasicTax(taxRegistrationNumber)
                + " </BasicTax>");
        if (manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber) > 0) {
            outputStream.println("<TaxIncrease> "
                + manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber)
                + " </TaxIncrease>");
        } else {
            outputStream.println("<TaxDecrease> "
                + manager.getTaxpayerVarTaxOnRec(taxRegistrationNumber)
                + " </TaxDecrease>");
        }
        outputStream.println("<TotalTax> "
                + manager.getTaxpayerTotalTax(taxRegistrationNumber)
                + " </TotalTax>");
        outputStream.println("<Receipts> "
                + manager.getTaxpayerTotalRecGathered(taxRegistrationNumber)
                + " </Receipts>");
        outputStream.println("<Entertainment> "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     ENTERTAINMENT)
                + " </Entertainment>");
        outputStream.println("<Basic> "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     BASIC)
                + " </Basic>");
        outputStream.println("<Travel> "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     TRAVEL)
                + " </Travel>");
        outputStream.println("<Health> "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     HEALTH)
                + " </Health>");
        outputStream.println("<Other> "
                + manager.getTaxpayerAmountOfRecKind(taxRegistrationNumber,
                                                     OTHER)
                + " </Other>");
        return outputStream;
    }

}
