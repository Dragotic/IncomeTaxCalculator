package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
    private static final double INCOME_LEVELS[] = {30390, 90000, 122110, 203390};
    private static final double TAXES[] = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    private static final double MIN_TAXES[] = {0, 1625.87, 5828.38, 8092.13, 14472.61};

    public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
        super(fullname, taxRegistrationNumber, income, INCOME_LEVELS, TAXES, MIN_TAXES);
    }

}
