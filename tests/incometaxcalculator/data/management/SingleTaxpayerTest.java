package incometaxcalculator.data.management;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class SingleTaxpayerTest {
    private Taxpayer taxpayer;

    @Test
    void calculateBasicTaxFirstLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(24680);
        double expected = 0.0535 * rndIncome;
        this.taxpayer = new SingleTaxpayer("Single Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxSecondLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(24680, 81080);
        double expected = 1320.38 + 0.0705 * (rndIncome - 24680);
        this.taxpayer = new SingleTaxpayer("Single Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxThirdLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(81080, 90000);
        double expected = 5296.58 + 0.0785 * (rndIncome - 81080);
        this.taxpayer = new SingleTaxpayer("Single Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFourthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt(90000, 152540);
        double expected = 5996.80 + 0.0785 * (rndIncome - 90000);
        this.taxpayer = new SingleTaxpayer("Single Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxFifthLevel() {
        int rndIncome = ThreadLocalRandom.current().nextInt() + 152540;
        double expected = 10906.19 + 0.0985 * (rndIncome - 152540);
        this.taxpayer = new SingleTaxpayer("Single Tax Payer", 999999999, rndIncome);
        assertEquals(expected, this.taxpayer.calculateBasicTax());
    }


}