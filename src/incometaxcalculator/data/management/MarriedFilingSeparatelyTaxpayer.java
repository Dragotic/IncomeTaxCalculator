package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {
    private static final double[] INCOME_LEVELS = {18040, 71680,
                                                    90000, 127120};
    private static final double[] TAX_LEVELS = {0.0535, 0.0705,
                                                0.0785, 0.0785, 0.0985};
    private static final double[] MIN_TAXES = {0, 965.14, 4746.76,
                                                6184.88, 9098.80};

    public MarriedFilingSeparatelyTaxpayer(final String fullname,
                                           final int taxRegistrationNumber,
                                           final float income) {
        super(fullname, taxRegistrationNumber, income,
                INCOME_LEVELS, TAX_LEVELS, MIN_TAXES);
    }

}
