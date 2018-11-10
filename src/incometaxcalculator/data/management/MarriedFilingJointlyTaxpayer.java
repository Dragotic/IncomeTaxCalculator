package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
    private static final double[] INCOME_LEVELS = {36080, 90000,
                                                    143350, 254240};
    private static final double[] TAX_LEVELS = {0.0535, 0.0705,
                                                0.0705, 0.0785, 0.0985};
    private static final double[] MIN_TAXES = {0, 1930.28, 5731.64,
                                                9492.82, 18197.69};

    public MarriedFilingJointlyTaxpayer(final String fullname,
                                        final int taxRegistrationNumber,
                                        final float income) {
        super(fullname, taxRegistrationNumber, income,
                INCOME_LEVELS, TAX_LEVELS, MIN_TAXES);
    }

}
