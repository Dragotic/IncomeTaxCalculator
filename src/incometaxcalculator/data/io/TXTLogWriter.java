package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogWriter extends LogWriter {

    @Override
    protected PrintWriter genFileSpecParts(final int taxRegNum)
            throws IOException {
        PrintWriter outputStream = new PrintWriter(taxRegNum + "_LOG.txt");
        printGeneralInfo(outputStream, taxRegNum);
        printTaxInfo(outputStream, taxRegNum);
        printReceiptInfo(outputStream, taxRegNum);
        return outputStream;
    }

    private void printGeneralInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("Name: "
                + manager.getTaxpayerName(taxRegNum));
        outputStream.println("AFM: "
                + taxRegNum);
        outputStream.println("Income: "
                + manager.getTaxpayerIncome(taxRegNum));
    }

    private void printTaxInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("Basic Tax: "
                + manager.getTaxpayerBasicTax(taxRegNum));
        if (getVarTax(taxRegNum) > 0) {
            outputStream.println("Tax Increase: " + getVarTax(taxRegNum));
        } else {
            outputStream.println("Tax Decrease: " + getVarTax(taxRegNum));
        }
        outputStream.println("Total Tax: " + getVarTax(taxRegNum));
    }

    private void printReceiptInfo(PrintWriter outputStream, int taxRegNum) {
        outputStream.println("TotalReceiptsGathered: "
                        + manager.getTaxpayerTotalRecGathered(taxRegNum));
        outputStream.println("Entertainment: "
                + getAmountOfRec(taxRegNum, ENTERTAINMENT));
        outputStream.println("Basic: " + getAmountOfRec(taxRegNum, BASIC));
        outputStream.println("Travel: " + getAmountOfRec(taxRegNum, TRAVEL));
        outputStream.println("Health: " + getAmountOfRec(taxRegNum, HEALTH));
        outputStream.println("Other: " + getAmountOfRec(taxRegNum, OTHER));
    }

    private float getAmountOfRec(int taxRegNum, short type) {
        return manager.getTaxpayerAmountOfRecKind(taxRegNum, type);
    }

    private Double getVarTax(int taxRegNum){
        return manager.getTaxpayerVarTaxOnRec(taxRegNum);
    }

}
