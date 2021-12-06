package fai.imp.farmaclick.csv;

import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Formatter.BigDecimalLayoutForm;

class FarmaclickNumberFormat extends NumberFormat {
  
  private static final long serialVersionUID = 1L;
  private int decimals = 0;
  
  public FarmaclickNumberFormat(int decimals) {
    this.decimals = decimals;
  }
  
  public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
    throw new NoSuchMethodError("non implementato");
  }

  public StringBuffer format(Object number, StringBuffer toAppendTo, FieldPosition pos) {
    throw new NoSuchMethodError("non implementato");
  }

  public Object parseObject(String source) throws ParseException {
    throw new NoSuchMethodError("non implementato");
  }

  public String toString() {
    throw new NoSuchMethodError("non implementato");
  }

  public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
    throw new NoSuchMethodError("non implementato");
  }

  public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
    throw new NoSuchMethodError("non implementato");
  }

  public Number parse(String source, ParsePosition parsePosition) {
    throw new NoSuchMethodError("non implementato");
  }

  public Number parse(String source) throws ParseException {
    if (source == null) return null;
    if (source.length() < decimals) throw new IllegalAccessError("attesa stringa di caratteri numerci con gli ultimi "+decimals+" da interpretare come cifre decimali; tuttavia, la stringa \""+source+"\" è lunga appena "+source.length()+" caratteri");
    String intPart = "0";
    if (source.length() > decimals) {
      intPart = source.substring(0, source.length()-decimals);
    }
    String decimalPart = "0";
    if (decimals > 0) {
      decimalPart = source.substring(source.length()-decimals, source.length());
    }
    Double d = new Double(intPart+"."+decimalPart);
    return d;
}

  public boolean isParseIntegerOnly() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setParseIntegerOnly(boolean value) {
    throw new NoSuchMethodError("non implementato");
  }

  public boolean isGroupingUsed() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setGroupingUsed(boolean newValue) {
    throw new NoSuchMethodError("non implementato");
  }

  public int getMaximumIntegerDigits() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setMaximumIntegerDigits(int newValue) {
    throw new NoSuchMethodError("non implementato");
  }

  public int getMinimumIntegerDigits() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setMinimumIntegerDigits(int newValue) {
    throw new NoSuchMethodError("non implementato");
  }

  public int getMaximumFractionDigits() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setMaximumFractionDigits(int newValue) {
    throw new NoSuchMethodError("non implementato");
  }

  public int getMinimumFractionDigits() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setMinimumFractionDigits(int newValue) {
    throw new NoSuchMethodError("non implementato");
  }

  public Currency getCurrency() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setCurrency(Currency currency) {
    throw new NoSuchMethodError("non implementato");
  }

  public RoundingMode getRoundingMode() {
    throw new NoSuchMethodError("non implementato");
  }

  public void setRoundingMode(RoundingMode roundingMode) {
    throw new NoSuchMethodError("non implementato");
  }
  
  public static void main(String[] args) throws Exception {
    
    System.out.println(new FarmaclickNumberFormat(0).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(1).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(2).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(3).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(4).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(5).parse("12345"));
    System.out.println(new FarmaclickNumberFormat(6).parse("12345"));
    
  }

}
