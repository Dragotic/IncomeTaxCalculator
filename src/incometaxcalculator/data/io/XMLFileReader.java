package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {

    @Override
    protected int checkSpecificReceipt(final String[] values) {
        if (values[0].equals("<ReceiptID>")) {
            return Integer.parseInt(values[1].trim());
        }
        return -1;
    }

    protected String getValueOfField(final String fieldsLine)
            throws WrongFileFormatException {
        try {
            return new StringBuilder(
                    new StringBuilder(fieldsLine.split(" ", 2)[1])
                    .reverse().toString().trim().split(" ", 2)[1])
                    .reverse().toString();
        } catch (NullPointerException e) {
            throw new WrongFileFormatException();
        }
    }

}
