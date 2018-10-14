package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TXTFileReaderTest {

    private TaxpayerManager taxpayerManager;

    @BeforeEach
    void setUp() {
        this.taxpayerManager = new TaxpayerManager();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void readFile()
            throws NumberFormatException, IOException, WrongTaxpayerStatusException,
            WrongFileFormatException, WrongFileEndingException, WrongReceiptKindException, WrongReceiptDateException {
        int taxRegistrationNumber = 123456789;
        String taxRegistrationNumberFile = taxRegistrationNumber + "_INFO.txt";
        if (taxpayerManager.containsTaxpayer())
        {
            assertFalse(taxpayerManager.containsTaxpayer());
        }
        this.taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);

        assertTrue(this.taxpayerManager.containsTaxpayer(taxRegistrationNumber));
    }
}