package incometaxcalculator.data.io;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

public abstract class InfoWriter implements FileWriter {

    protected TaxpayerManager manager = new TaxpayerManager();

    protected abstract PrintWriter generateFileSpecificParts(int taxRegistrationNumber) throws IOException;

    protected abstract void generateReceiptsSpecificParts(Receipt receipt, PrintWriter outputStream);

    public void generateFile(int taxRegistrationNumber) throws IOException {
        PrintWriter outputStream = generateFileSpecificParts(taxRegistrationNumber);
        generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
        outputStream.close();
    }

    private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {
        HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
        Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry<Integer, Receipt> entry = iterator.next();
            Receipt receipt = entry.getValue();
            generateReceiptsSpecificParts(receipt, outputStream);
            outputStream.println();
        }
    }

}
