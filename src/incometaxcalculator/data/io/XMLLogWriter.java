package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLLogWriter extends LogWriter {

    @Override
    protected PrintWriter genFileSpecParts(final int taxRegNum)
            throws IOException {
        PrintWriter outputStream = new PrintWriter(taxRegNum + "_LOG.xml");
        printGeneralInfo(outputStream, taxRegNum);
        printTaxInfo(outputStream, taxRegNum);
        printReceiptInfo(outputStream, taxRegNum);
        return outputStream;
    }

    private void printGeneralInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("<Name> "
                + manager.getTaxpayerName(taxRegNum) + " </Name>");
        outputStream.println("<AFM> "
                + taxRegNum + " </AFM>");
        outputStream.println("<Income> "
                + manager.getTaxpayerIncome(taxRegNum) + " </Income>");
    }

    private void printTaxInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("<BasicTax> "
                + manager.getTaxpayerBasicTax(taxRegNum) + " </BasicTax>");
        Double varTax = manager.getTaxpayerVarTaxOnRec(taxRegNum);
        if (varTax > 0) {
            outputStream.println("<TaxIncrease> " + varTax + " </TaxIncrease>");
        } else { outputStream.println("<TaxDecrease> " + varTax + " </TaxDecrease>"); }
        outputStream.println("<TotalTax> "
                + manager.getTaxpayerTotalTax(taxRegNum) + " </TotalTax>");
    }

    private void printReceiptInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("<Receipts> "
                + manager.getTaxpayerTotalRecGathered(taxRegNum) + " </Receipts>");
        outputStream.println("<Entertainment> "
                + getAmountOfRec(taxRegNum, ENTERTAINMENT) + " </Entertainment>");
        outputStream.println("<Basic> " + getAmountOfRec(taxRegNum, BASIC) + " </Basic>");
        outputStream.println("<Travel> " + getAmountOfRec(taxRegNum, TRAVEL) + " </Travel>");
        outputStream.println("<Health> " + getAmountOfRec(taxRegNum, HEALTH) + " </Health>");
        outputStream.println("<Other> " + getAmountOfRec(taxRegNum, OTHER) + " </Other>");
    }

    private float getAmountOfRec(int taxRegNum, short type){
        return manager.getTaxpayerAmountOfRecKind(taxRegNum, type);
    }

}
