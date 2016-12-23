package im.dadoo.turtle.po;

public class HistoryPo {

  private String code;

  private String date;

  private double open;

  private double close;

  private double high;

  private double low;

  private double atr;

  private double n;

  private int status;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("HistoryPo{");
    sb.append("code='").append(code).append('\'');
    sb.append(", date='").append(date).append('\'');
    sb.append(", open=").append(open);
    sb.append(", close=").append(close);
    sb.append(", high=").append(high);
    sb.append(", low=").append(low);
    sb.append(", atr=").append(atr);
    sb.append(", n=").append(n);
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getAtr() {
    return atr;
  }

  public void setAtr(double atr) {
    this.atr = atr;
  }

  public double getN() {
    return n;
  }

  public void setN(double n) {
    this.n = n;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
