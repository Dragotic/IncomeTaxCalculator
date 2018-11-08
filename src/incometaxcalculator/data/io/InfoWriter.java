package incometaxcalculator.data.io;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class InfoWriter implements FileWriter {

    protected TaxpayerManager manager = new TaxpayerManager();

    protected abstract PrintWriter
    geneFileSpecParts(int taxRegistrationNumber) throws IOException;

    protected abstract void
    genReceiptsSpecParts(Receipt receipt,
                                  PrintWriter outputStream);

    public void generateFile(final int taxRegistrationNumber)
            throws IOException {
        PrintWriter outputStream =
                geneFileSpecParts(taxRegistrationNumber);
        generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
        outputStream.close();
    }

    private void generateTaxpayerReceipts(final int taxRegistrationNumber,
                                          final PrintWriter outputStream) {
        HashMap<Integer, Receipt> receiptsHashMap = manager
                .getReceiptHashMap(taxRegistrationNumber);
        for (Map.Entry<Integer, Receipt> entry : receiptsHashMap
                .entrySet()) {
            genReceiptsSpecParts(entry.getValue(), outputStream);
            outputStream.println();
        }
    }

}
