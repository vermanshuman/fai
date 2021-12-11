package fai.common.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class NumberFormatFactory {


    /**
     * Simile a {@link #newNumberFormat(String, String, char, char)}, ma
     * consente di indicare il formato comune ai valori negativi e positivi
     * nel caso in cui il formato coincida a meno del segno, che sar�
     * non-rappresentato per i positivi, e il carattere "-" per i negativi
     *
     * @param absoluteValueFormat
     * @param decimalSeparator
     * @param groupingSeparator
     * @return
     */
    public static NumberFormat newNumberFormat(String absoluteValueFormat, char decimalSeparator, char groupingSeparator) {
        return newNumberFormat(absoluteValueFormat, "-" + absoluteValueFormat, decimalSeparator, groupingSeparator);
    }


    /**
     * Esempio di utilizzo:
     * <pre>
     *   NumberFormat nf = NumberFormatFactory.newNumberFormat("#,##0.00", "-#,##0.00", ',', '\'');
     *   System.out.println(nf.format(-111222333.454));
     * </pre>
     * output:
     * <pre>
     *   -111'222'333,45
     * </pre>
     *
     * @param positiveFormat
     * @param negativeFormat
     * @param decimalSeparator
     * @param groupingSeparator
     * @return
     */
    public static NumberFormat newNumberFormat(String positiveFormat, String negativeFormat, char decimalSeparator, char groupingSeparator) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(decimalSeparator);
        dfs.setGroupingSeparator(groupingSeparator);
        NumberFormat df = new DecimalFormat(positiveFormat + ";" + negativeFormat, dfs); // es. "###0.0;-###0.0"
        return df;

    }

}
