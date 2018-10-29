package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class LogWriter implements FileWriter {

    protected static final short ENTERTAINMENT = 0;
    protected static final short BASIC = 1;
    protected static final short TRAVEL = 2;
    protected static final short HEALTH = 3;
    protected static final short OTHER = 4;

    protected TaxpayerManager manager = new TaxpayerManager();

    protected abstract PrintWriter generateFileSpecificParts(int taxRegistrationNumber) throws IOException;

    public void generateFile(int taxRegistrationNumber) throws IOException {
        PrintWriter outputStream = generateFileSpecificParts(taxRegistrationNumber);
        outputStream.close();
    }

}
