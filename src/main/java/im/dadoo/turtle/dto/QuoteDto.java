package im.dadoo.turtle.dto;

public class QuoteDto {

  private String code;

  private int market;

  private String time;

  private double price;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("QuoteDto{");
    sb.append("code='").append(code).append('\'');
    sb.append(", market=").append(market);
    sb.append(", time='").append(time).append('\'');
    sb.append(", price=").append(price);
    sb.append('}');
    return sb.toString();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getMarket() {
    return market;
  }

  public void setMarket(int market) {
    this.market = market;
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
}
