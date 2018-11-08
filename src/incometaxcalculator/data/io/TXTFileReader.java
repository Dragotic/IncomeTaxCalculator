package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

    @Override
    protected int checkSpecificReceipt(final String[] values) {
        if (values[0].equals("Receipt")) {
            if (values[1].equals("ID:")) {
                return Integer.parseInt(values[2].trim());
            }
        }
        return -1;
    }

    protected String getValueOfField(final String fieldsLine)
            throws WrongFileFormatException {
        try {
            return (fieldsLine.split(" ", 2))[1].trim();
        } catch (NullPointerException e) {
            throw new WrongFileFormatException();
        }
    }

}
