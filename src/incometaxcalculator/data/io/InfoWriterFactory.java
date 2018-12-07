package incometaxcalculator.data.io;

import java.io.File;

public final class InfoWriterFactory {

    private InfoWriterFactory() {
    }

    public static InfoWriter[] createInfoWriter(final int taxRegistrationNumber) {
        InfoWriter[] writers = new InfoWriter[2];
        if (!(new File(taxRegistrationNumber + "_INFO.xml").exists())) {
            writers[0] = new TXTInfoWriter();
        } else {
            writers[0] = new XMLInfoWriter();
            if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
                writers[1] = new TXTInfoWriter();
            }
        }
        return writers;
    }
}
