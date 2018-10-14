package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TXTInfoWriterTest {

    private static final int taxRegistrationNumber = 121212121;
    private TXTInfoWriter txtInfoWriter;
    private TaxpayerManager taxpayerManager;

    @BeforeEach
    void setUp(){
        txtInfoWriter = new TXTInfoWriter();
        taxpayerManager = new TaxpayerManager();

        try {
            taxpayerManager.createTaxpayer("Test User", taxRegistrationNumber, "Single", 20000);
            taxpayerManager.createReceipt(12344444, "12/10/2018", 20, "Other", "Zara", "Greece", "Athens", "Ermou", 10, taxRegistrationNumber);
        } catch (WrongTaxpayerStatusException | WrongReceiptDateException | WrongReceiptKindException e) {
            e.printStackTrace();
        }

    }

    @Test
    void writeInfoToTxt() throws WrongTaxpayerStatusException, WrongReceiptDateException, IOException, WrongFileFormatException, WrongFileEndingException, WrongReceiptKindException {
        try {
            this.txtInfoWriter.generateFile(taxRegistrationNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        taxpayerManager.loadTaxpayer(taxRegistrationNumber + "_INFO.txt");
        assertTrue(taxpayerManager.containsTaxpayer(taxRegistrationNumber));
    }

}
