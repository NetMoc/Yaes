/**
 * TEXT UI: part of the YAES simulator
 * 
 * Lotzi Boloni 2006
 */
package yaes.ui.text;



public class TextUiHelper {

    public static String createHeader(final String text) {
        return createHeader(text, "-", 64);
    }

    /**
     * Creates a three line header from the passed text
     * 
     * @return
     */
    public static String createHeader(final String text, String element,
            int length) {
        final StringBuffer buffer = new StringBuffer();
        String line = line(element, length);
        buffer.append(line + "\n");
        if (length > 100) {
            buffer.append("\n");
        }
        final int whiteSpaces = (length - text.length()) / 2;
        if (whiteSpaces > 0) {
            buffer.append(line(" ", whiteSpaces));
        }
        buffer.append(text + "\n");
        if (length > 100) {
            buffer.append("\n");
        }
        buffer.append(line + "\n");
        return buffer.toString();
    }

    /**
     * <p>
     * Creates a string for a labeled separator.
     * 
     * @return
     */
    public static String createLabeledSeparator(final String label) {
        final StringBuffer buffer = new StringBuffer();
        String line = line("-", 64);
        if (label.equals("-")) {
            buffer.append(line);
            buffer.append("\n");
            return buffer.toString();
        }
        final String realLabel = " " + label.substring(1) + " ";
        final int pads = 64 - realLabel.length();
        if (pads < 0) {
            buffer.append("---" + realLabel + "---");
            buffer.append("\n");
            return buffer.toString();
        }
        int i = 0;
        for (i = 0; i < pads / 2; i++) {
            buffer.append("-");
        }
        buffer.append(realLabel);
        for (; i < pads; i++) {
            buffer.append("-");
        }
        buffer.append("\n");
        return buffer.toString();
    }

    
    /**
     * Formats the data size in B, KB, MB or GB, TB as needed
     * @return
     */
    public static String formatDataSize(long size) {
        double dsize = size;
        String retval = "";
        if(dsize < 1024.0) {
            retval = size + " B";
            return retval;
        }
        if (dsize < 1024.0 * 1024.0) {
            double val = Math.round((dsize / 1024.0) * 10.0) / 10.0;
            retval = val + " KB";
            return retval;
        }
        if (dsize < 1024.0 * 1024.0 * 1024.0) {
            double val = Math.round((dsize / (1024.0 * 1024.0)) * 10.0) / 10.0;
            retval = val + " MB";
            return retval;
        }
        if (dsize < 1024.0 * 1024.0 * 1024.0 * 1024.0) {
            double val = Math.round((dsize / (1024.0 * 1024.0 * 1024.0)) * 10.0) / 10.0;
            retval = val + " GB";
            return retval;
        }
        double val = Math.round((dsize / (1024.0 * 1024.0 * 1024.0 * 1024.0)) * 10.0) / 10.0;
        retval = val + " TB";
        return retval;        
    }
    
    
    
    /**
     * Formats a time interval specified in seconds. Falls back to the 
     * milliseconds model
     * @param timeSeconds
     * @return
     */
    public static String formatTimeInterval(double timeSeconds) {
        long milliseconds = Math.round(timeSeconds * 1000);
        return formatTimeInterval(milliseconds);
    }
    
    
    
    /**
     * Formats a time interval in an approximate way. Used for time estimates
     * etc.
     * 
     * @param milliseconds
     * @return
     */
    public static String formatTimeInterval(final long milliseconds) {
        int seconds = (int) (milliseconds / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        final int days = hours / 24;
        if (days != 0) {
            String ret = "" + days + " days";
            hours = hours % 24;
            if (hours != 0) {
                ret = ret + " and " + hours + " hours";
            }
            return ret;
        }
        if (hours != 0) {
            String ret = "" + hours + " hours";
            minutes = minutes % 60;
            if (minutes != 0) {
                ret = ret + " and " + minutes + " minutes";
                return ret;
            }
            return ret;
        }
        if (minutes != 0) {
            String ret = "" + minutes + " minutes";
            seconds = seconds % 60;
            if (seconds != 0) {
                ret = ret + " and " + seconds + " seconds";
                return ret;
            }
            return ret;
        }
        return seconds + " seconds";
    }
    
    
    

    /**
     * <p>
     * Shifts a multiline text to right with 4 spaces
     * 
     * <p>
     * Useful for formatting hierarchical classes.
     * 
     * @param pads
     * @param padding
     * @param text
     * @return
     */
    public static String indent(final int pads, final String text) {
        return TextUiHelper.shiftText(pads, " ", text);
    }

    /**
     * <p>
     * Shifts a multiline text to right with 4 spaces
     * 
     * <p>
     * Useful for formatting hierarchical classes.
     * 
     * @param pads
     * @param padding
     * @param text
     * @return
     */
    public static String indent(final String text) {
        return TextUiHelper.shiftText(4, " ", text);
    }

    public static String line(String element, int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i != length; i++) {
            buffer.append(element);
        }
        return buffer.toString();
    }

    /**
     * Shifts a multiline text to right the specified number of characters and
     * the specified padding character
     * 
     * @param pads
     * @param padding
     * @param text
     * @return
     */
    public static String shiftText(final int pads, final String padding,
            String text) {
        String paddingString = "";
        boolean newLineAtEnd = false;
        for (int i = 0; i != pads; i++) {
            paddingString = paddingString + padding;
        }
        if (text.endsWith("\n")) {
            text = text.substring(0, text.length() - 1);
            newLineAtEnd = true;
        }
        final String preparedText = paddingString
                + text.replaceAll("\n", "\n" + paddingString);
        if (newLineAtEnd) {
            return preparedText + "\n";
        } else {
            return preparedText;
        }
    }


    /**
     * Pad to a string of a certain length with spaces at the left
     * 
     * @param nodeType
     * @param length
     * @return
     */
    public static String padTo(Object object, int length) {
        String text = object.toString();
        if (text.length() > length) {
            return text.substring(0, length);
        }
        int count = length - text.length();
        StringBuffer buff = new StringBuffer(text);
        for(int i = 0; i != count; i++) {
            buff.append(" ");
        }
        return buff.toString();
    }
    
    
    /**
     * Wrapping a text string to a certain width
     * 
     * @param in
     * @param width
     * @return
     */
    public static String wrap(String in, int width) {
        in = in.trim();
        if (in.length() < width) {
            return in;
        }
        if (in.substring(0, width).contains("\n")) {
            return in.substring(0, in.indexOf("\n")).trim()
                    + "\n\n"
                    + TextUiHelper.wrap(in.substring(in.indexOf("\n") + 1),
                            width);
        }
        // calculates the location of the first space, tab or -
        int place =
                Math.max(
                        Math.max(in.lastIndexOf(" ", width),
                                in.lastIndexOf("\t", width)),
                        in.lastIndexOf("-", width));
        if (place == -1) {
            TextUi.errorPrint("Cannot wrap " + in + " to " + width);
            place = width;
            // return in;
        }
        return in.substring(0, place).trim() + "\n"
                + TextUiHelper.wrap(in.substring(place), width);
    }

}
