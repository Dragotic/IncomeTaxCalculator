package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public final class LogWriterFactory {

    private LogWriterFactory() {
    }

    public static LogWriter createLogWriter(final String fileFormat)
            throws WrongFileFormatException {

        if (fileFormat.equals("txt")) {
            return new TXTLogWriter();
        } else if (fileFormat.equals("xml")) {
            return new XMLLogWriter();
        } else {
            throw new WrongFileFormatException();
        }
    }
}
