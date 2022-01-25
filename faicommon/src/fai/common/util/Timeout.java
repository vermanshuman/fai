package fai.common.util;

public class Timeout {
  
  private long timeoutMillis;
  private long lastExpiredMillis = 0;
  
  public Timeout(long timeoutMillis, boolean startExpired) {
    super();
    this.timeoutMillis = timeoutMillis;
    lastExpiredMillis = startExpired ? 0 : System.currentTimeMillis();
  }
  
  public boolean isExpired() {
    if (System.currentTimeMillis() - lastExpiredMillis >= timeoutMillis) {
      restart();
      return true;
    }
    return false;
  }
  
  public Timeout restart() {
    lastExpiredMillis = System.currentTimeMillis();
    return this;
  }
  
  public Timeout expire() {
    lastExpiredMillis = 0;
    return this;
  }
  
  
}
