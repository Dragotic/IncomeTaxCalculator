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
        try {
            this.taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
        } catch (NumberFormatException e) {
            System.out.println("The tax registration number must have only digits.");
        } catch (IOException e) {
            System.out.println("The file doesn't exist.");
        } catch (WrongFileFormatException e) {
            System.out.println("Please check your file format and try again.");
        } catch (WrongFileEndingException e) {
            System.out.println("Please check your file ending and try again.");
        } catch (WrongTaxpayerStatusException e) {
            System.out.println("Please check taxpayer's status and try again.");
        } catch (WrongReceiptDateException e) {
            System.out.println("Please make sure your date is DD/MM/YYYY and try again.");
        } catch (WrongReceiptKindException e) {
            System.out.println("Please check receipts kind and try again.");
        }
        assertTrue(this.taxpayerManager.containsTaxpayer(taxRegistrationNumber));
    }
}