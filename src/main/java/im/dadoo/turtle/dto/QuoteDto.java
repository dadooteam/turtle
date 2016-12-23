package im.dadoo.turtle.dto;

public class QuoteDto {

  private String name;

  private String time;

  private double price;

  private double open;

  private double lastClose;

  private double high;

  private double low;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("QuoteDto{");
    sb.append("name='").append(name).append('\'');
    sb.append(", time='").append(time).append('\'');
    sb.append(", price=").append(price);
    sb.append(", open=").append(open);
    sb.append(", lastClose=").append(lastClose);
    sb.append(", high=").append(high);
    sb.append(", low=").append(low);
    sb.append('}');
    return sb.toString();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getLastClose() {
    return lastClose;
  }

  public void setLastClose(double lastClose) {
    this.lastClose = lastClose;
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
}
