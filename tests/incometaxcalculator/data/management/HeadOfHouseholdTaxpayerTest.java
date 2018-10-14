package incometaxcalculator.data.management;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class HeadOfHouseholdTaxpayerTest {
    private Taxpayer taxpayer;

    @Test
    void calculateBasicTaxFirstLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(30390);
        double expected = 0.0535 * rndIncome;
        this.taxpayer = new HeadOfHouseholdTaxpayer("Head of House Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxSecondLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(30390, 90000);
        double expected = 1625.87 + 0.0705 * (rndIncome - 30390);
        this.taxpayer = new HeadOfHouseholdTaxpayer("Head of Household Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxThirdLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(90000, 122110);
        double expected = 5828.38 + 0.0705 * (rndIncome - 90000);
        this.taxpayer = new HeadOfHouseholdTaxpayer("Head of Household Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFourthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(122110, 203390);
        double expected = 8092.13 + 0.0785 * (rndIncome - 122110);
        this.taxpayer = new HeadOfHouseholdTaxpayer("Head of Household Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFifthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(203390, 3 * 203390);
        double expected = 14472.61 + 0.0985 * (rndIncome - 203390);
        this.taxpayer = new HeadOfHouseholdTaxpayer("Head of Household Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }


}