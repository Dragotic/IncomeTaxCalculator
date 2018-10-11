package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MarriedFilingSeparatelyTaxpayerTest {
    private Taxpayer taxpayer;

    @Test
    void calculateBasicTaxFirstLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(18040);
        double expected = 0.0535 * rndIncome;
        this.taxpayer = new MarriedFilingSeparatelyTaxpayer("Married Filing Seperately Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxSecondLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(18040, 71680);
        double expected = 965.14 + 0.0705 * (rndIncome - 18040);
        this.taxpayer = new MarriedFilingSeparatelyTaxpayer("Married Filing Seperately Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxThirdLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(71680, 90000);
        double expected = 4746.76 + 0.0785 * (rndIncome - 71680);
        this.taxpayer = new MarriedFilingSeparatelyTaxpayer("Married Filing Seperately Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFourthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(90000, 127120);
        double expected = 6184.88 + 0.0785 * (rndIncome - 90000);
        this.taxpayer = new MarriedFilingSeparatelyTaxpayer("Married Filing Seperately Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFifthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt() + 127120;
        double expected = 9098.80 + 0.0985 * (rndIncome - 127120);
        this.taxpayer = new MarriedFilingSeparatelyTaxpayer("Married Filing Seperately Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

}