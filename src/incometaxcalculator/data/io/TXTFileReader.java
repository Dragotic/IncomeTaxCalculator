package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

    @Override
    protected int checkSpecificReceipt(String[] values) {
        if (values[0].equals("Receipt")) {
            if (values[1].equals("ID:")) {
                int receiptId = Integer.parseInt(values[2].trim());
                return receiptId;
            }
        }
        return -1;
    }

    protected String getValueOfField(String fieldsLine) throws WrongFileFormatException {
        if (isEmpty(fieldsLine)) {
            throw new WrongFileFormatException();
        }
        try {
            String values[] = fieldsLine.split(" ", 2);
            values[1] = values[1].trim();
            return values[1];
        } catch (NullPointerException e) {
            throw new WrongFileFormatException();
        }
    }

}