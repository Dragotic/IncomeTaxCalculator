package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

    private final String fullname;
    private final int taxRegistrationNumber;
    private final float income;
    private final double[] incomeLevels;
    private final double[] minTaxes;
    private final double[] taxLevels;
    private float[] amountPerReceiptsKind = new float[5];
    private int totalReceiptsGathered = 0;
    private HashMap<Integer, Receipt> receiptHashMap =
            new HashMap<Integer, Receipt>(0);
    private final String[] receiptKinds = {"Entertainment", "Basic",
                                            "Travel", "Health",
                                            "Other"};
    private static final double[] VAR_TAX_LEVELS = {0.2, 0.4, 0.6};
    private static final double[] VAR_TAXES = {0.08, 0.04, -0.15};

    public double calculateBasicTax() {
        for (int i = 0; i < incomeLevels.length; i++) {
            if (income < incomeLevels[i]) {
                if (i == 0) {
                    return taxLevels[i] * income;
                }
                return minTaxes[i] + taxLevels[i]
                        * (income - incomeLevels[i - 1]);
            }
        }

        return minTaxes[minTaxes.length - 1]
                + taxLevels[taxLevels.length - 1]
                * (income - incomeLevels[incomeLevels.length - 1]);
    }

    protected Taxpayer(final String fullname, final int taxRegistrationNumber,
                       final float income, final double[] incomeLevels,
                       final double[] taxLevels, final double[] minTaxes) {
        this.fullname = fullname;
        this.taxRegistrationNumber = taxRegistrationNumber;
        this.income = income;
        this.incomeLevels = incomeLevels;
        this.taxLevels = taxLevels;
        this.minTaxes = minTaxes;
    }

    public void addReceipt(final Receipt receipt) throws WrongReceiptKindException {
        for (int i = 0; i < amountPerReceiptsKind.length; i++) {
            if (receipt.getKind().equals(receiptKinds[i])) {
                amountPerReceiptsKind[i] += receipt.getAmount();
                receiptHashMap.put(receipt.getId(), receipt);
                totalReceiptsGathered++;
                break;
            }
            if (i == 4) {
                throw new WrongReceiptKindException();
            }
        }
    }

    public void removeReceipt(final int receiptId) throws WrongReceiptKindException {
        Receipt receipt = receiptHashMap.get(receiptId);
        for (int i = 0; i < amountPerReceiptsKind.length; i++) {
            if (receipt.getKind().equals(receiptKinds[i])) {
                amountPerReceiptsKind[i] -= receipt.getAmount();
                receiptHashMap.remove(receiptId);
                totalReceiptsGathered--;
                break;
            }
            if (i == 4) {
                throw new WrongReceiptKindException();
            }
        }
    }

    public String getFullname() {
        return fullname;
    }

    public int getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public float getIncome() {
        return income;
    }

    public HashMap<Integer, Receipt> getReceiptHashMap() {
        return receiptHashMap;
    }

    public double getVariationTaxOnReceipts() {
        float totalAmountOfReceipts = getTotalAmountOfReceipts();
        for (int i = 0; i < 3; i++) {
            if (totalAmountOfReceipts < VAR_TAX_LEVELS[i] * income) {
                return calculateBasicTax() * VAR_TAXES[i];
            }
        }
        return calculateBasicTax() * -0.30;
    }

    private float getTotalAmountOfReceipts() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += amountPerReceiptsKind[i];
        }
        return sum;
    }

    public int getTotalReceiptsGathered() {
        return totalReceiptsGathered;
    }

    public float getAmountOfReceiptKind(final short kind) {
        return amountPerReceiptsKind[kind];
    }

    public double getTotalTax() {
        return calculateBasicTax() + getVariationTaxOnReceipts();
    }

    public double getBasicTax() {
        return calculateBasicTax();
    }

}
