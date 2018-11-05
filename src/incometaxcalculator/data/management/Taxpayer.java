package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

    protected final String fullname;
    protected final int taxRegistrationNumber;
    protected final float income;
    private final double incomeLevels[];
    private final double min_taxes[];
    private final double taxLevels[];
    private float amountPerReceiptsKind[] = new float[5];
    private int totalReceiptsGathered = 0;
    private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
    private final String receiptKinds[] = {"Entertainment", "Basic", "Travel", "Health", "Other"};

    public double calculateBasicTax() {
        for (int i = 0; i < incomeLevels.length; i++) {
            if (income < incomeLevels[i]) {
                if (i == 0) {
                    return taxLevels[i] * income;
                }
                return min_taxes[i] + taxLevels[i] * (income - incomeLevels[i-1]);
            }
        }

        return min_taxes[min_taxes.length - 1] + taxLevels[taxLevels.length - 1] * (income - incomeLevels[incomeLevels.length - 1]);
    }

    protected Taxpayer(String fullname, int taxRegistrationNumber, float income, double incomeLevels[], double taxLevels[], double min_taxes[]) {
        this.fullname = fullname;
        this.taxRegistrationNumber = taxRegistrationNumber;
        this.income = income;
        this.incomeLevels = incomeLevels;
        this.taxLevels = taxLevels;
        this.min_taxes = min_taxes;
    }

    public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
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

    public void removeReceipt(int receiptId) throws WrongReceiptKindException {
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
        double[] levels = {0.2, 0.4, 0.6};
        double[] variations = {0.08, 0.04, -0.15};
        for(int i = 0; i < 3; i++) {
            if (totalAmountOfReceipts < levels[i] * income) {
                return calculateBasicTax() * variations[i];
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

    public float getAmountOfReceiptKind(short kind) {
        return amountPerReceiptsKind[kind];
    }

    public double getTotalTax() {
        return calculateBasicTax() + getVariationTaxOnReceipts();
    }

    public double getBasicTax() {
        return calculateBasicTax();
    }

}