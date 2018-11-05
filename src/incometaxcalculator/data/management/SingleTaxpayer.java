package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {
    private static final double INCOME_LEVELS[] = {24680, 81080, 90000, 152540};
    private static final double TAX_LEVELS[] = {0.0535, 0.0705, 0.0785, 0.0785, 0.0985};
    private static final double MIN_TAXES[] = {0, 1320.38, 5296.58, 5996.80, 10906.19};

    public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
        super(fullname, taxRegistrationNumber, income, INCOME_LEVELS, TAX_LEVELS, MIN_TAXES);
    }

}
