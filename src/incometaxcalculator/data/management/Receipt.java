package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongReceiptDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Receipt {

    private int id;
    private final String issueDate;
    private final float amount;
    private final String kind;
    private final Company company;

    public Receipt(final int id, final String issueDate,
                   final float amount, final String kind,
                   final Company company)
            throws WrongReceiptDateException {
        this.id = id;
        this.issueDate = createDate(issueDate);
        this.amount = amount;
        this.kind = kind;
        this.company = company;
    }

    private String createDate(final String issueDate)
            throws WrongReceiptDateException {
        String[] token = issueDate.split("/");
        if (token.length != 3) {
            throw new WrongReceiptDateException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(issueDate, formatter);
        return localDate.format(formatter);
    }

    public int getId() {
        return id;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public float getAmount() {
        return amount;
    }

    public String getKind() {
        return kind;
    }

    public Company getCompany() {
        return company;
    }
}
