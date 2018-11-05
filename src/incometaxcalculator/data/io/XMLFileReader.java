package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {

    @Override
    protected int checkSpecificReceipt(String[] values) {
        if (values[0].equals("<ReceiptID>")) {
            int receiptId = Integer.parseInt(values[1].trim());
            return receiptId;
        }
        return -1;
    }

    protected String getValueOfField(String fieldsLine) throws WrongFileFormatException {
        if (isEmpty(fieldsLine)) {
            throw new WrongFileFormatException();
        }
        try {
            String valueWithTail[] = fieldsLine.split(" ", 2);
            String valueReversed[] = new StringBuilder(valueWithTail[1]).reverse().toString().trim()
                    .split(" ", 2);
            return new StringBuilder(valueReversed[1]).reverse().toString();
        } catch (NullPointerException e) {
            throw new WrongFileFormatException();
        }
    }

}
