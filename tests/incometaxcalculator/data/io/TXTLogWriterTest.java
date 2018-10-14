package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TXTLogWriterTest {

    private static final int taxRegistrationNumber = 121212121;
    private TXTLogWriter txtLogWriter;
    private TaxpayerManager taxpayerManager;

    @BeforeEach
    void setUp(){
        txtLogWriter = new TXTLogWriter();
        taxpayerManager = new TaxpayerManager();

        try {
            taxpayerManager.createTaxpayer("Test User", taxRegistrationNumber, "Single", 20000);
        } catch (WrongTaxpayerStatusException e) {
            e.printStackTrace();
        }

    }

    @Test
    void writeLogToTxt() throws IOException {

        this.txtLogWriter.generateFile(taxRegistrationNumber);

        BufferedReader inputStream = new BufferedReader(new java.io.FileReader(taxRegistrationNumber + "_LOG.txt"));
        assertTrue(inputStream.readLine().contains("Name: Test User"));
        assertTrue(inputStream.readLine().contains(Integer.toString(taxRegistrationNumber)));

    }

}
