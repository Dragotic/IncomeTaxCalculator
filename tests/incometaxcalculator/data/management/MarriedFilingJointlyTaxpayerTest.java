package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MarriedFilingJointlyTaxpayerTest {
    private Taxpayer taxpayer;

    @Test
    void calculateBasicTaxFirstLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(36080);
        double expected = 0.0535 * rndIncome;
        this.taxpayer = new MarriedFilingJointlyTaxpayer("Married Filling Jointly Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxSecondLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(36080, 90000);
        double expected = 1930.28 + 0.0705 * (rndIncome - 36080);
        this.taxpayer = new MarriedFilingJointlyTaxpayer("Married Filling Jointly Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxThirdLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(90000, 143350);
        double expected = 5731.64 + 0.0705 * (rndIncome - 90000);
        this.taxpayer = new MarriedFilingJointlyTaxpayer("Married Filling Jointly Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFourthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(143350, 254240);
        double expected = 9492.82 + 0.0785 * (rndIncome - 143350);
        this.taxpayer = new MarriedFilingJointlyTaxpayer("Married Filling Jointly Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFifthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt() + 254240;
        double expected = 18197.69 + 0.0985 * (rndIncome - 254240);
        this.taxpayer = new MarriedFilingJointlyTaxpayer("Married Filling Jointly Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }
}