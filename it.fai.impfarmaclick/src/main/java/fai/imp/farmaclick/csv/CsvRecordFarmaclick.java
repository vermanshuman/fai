package fai.imp.farmaclick.csv;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import fai.common.csv.CsvException;
import fai.common.csv.CsvFixedSpaceRecord;

public abstract class CsvRecordFarmaclick extends CsvFixedSpaceRecord {
  
  protected NumberFormat nf1 = new FarmaclickNumberFormat(1);
  protected NumberFormat nf2 = new FarmaclickNumberFormat(2);
  protected NumberFormat nf4 = new FarmaclickNumberFormat(4);
  protected NumberFormat nf6 = new FarmaclickNumberFormat(6);
  
  protected SimpleDateFormat sdfAAAAMMGG = new SimpleDateFormat("yyyyMMdd");
  
  protected int lineNumber;

  public CsvRecordFarmaclick(int lineNumber, String line) {
    super(line);
    this.lineNumber = lineNumber;
  }
  
  public int getLineNumber() {
    return lineNumber;
  }
  
  public abstract String getExpectedTipoRecord();
  
  public String getTipoRecord() {
    String s = getString(true, 8, 1, true, "Tipo Record");
    final String EXPECTED_VALUE = getExpectedTipoRecord();
    if (!EXPECTED_VALUE.equals(s)) {
      throw new CsvException("trovato Tipo Record"+s+"; valori atteso: "+EXPECTED_VALUE);
    }
    return s;
  }

  public String getFineRecord() {
    String s = getString(true, 278, 1, true, "Fine Record");
    final String EXPECTED_VALUE = "X";
    if (!EXPECTED_VALUE.equals(s)) {
      throw new CsvException("trovato Fine Record "+s+"; valori ammessi: "+EXPECTED_VALUE+" (riga: "+getLine()+")");
    }
    return s;
  }

  
}
