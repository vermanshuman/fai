package fai.common.csv;

public class CsvException extends RuntimeException {
  
  public CsvException(Throwable cause) {
    super(cause);
  }
  
  public CsvException(String message) {
    super(message);
  }
  
  public CsvException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
