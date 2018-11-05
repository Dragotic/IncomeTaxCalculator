package incometaxcalculator.data.io;

import java.io.File;

public class InfoWriterFactory {

    public static InfoWriter createInfoWriter(int taxRegistrationNumber) {
        return !(new File(taxRegistrationNumber + "_INFO.xml").exists()) ? new TXTInfoWriter() : new TXTInfoWriter();
    }
}
