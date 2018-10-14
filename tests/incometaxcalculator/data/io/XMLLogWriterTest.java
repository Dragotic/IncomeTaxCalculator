package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XMLLogWriterTest {

    private static final int taxRegistrationNumber = 121212121;
    private XMLLogWriter xmlLogWriter;
    private TaxpayerManager taxpayerManager;

    @BeforeEach
    void setUp(){
        xmlLogWriter = new XMLLogWriter();
        taxpayerManager = new TaxpayerManager();

        try {
            taxpayerManager.createTaxpayer("Test User", taxRegistrationNumber, "Single", 20000);
        } catch (WrongTaxpayerStatusException e) {
            e.printStackTrace();
        }

    }

    @Test
    void writeLogToTxt() throws IOException {

        this.xmlLogWriter.generateFile(taxRegistrationNumber);

        BufferedReader inputStream = new BufferedReader(new java.io.FileReader(taxRegistrationNumber + "_LOG.xml"));
        assertTrue(inputStream.readLine().contains("Test User"));
        assertTrue(inputStream.readLine().contains(Integer.toString(taxRegistrationNumber)));

    }

}
