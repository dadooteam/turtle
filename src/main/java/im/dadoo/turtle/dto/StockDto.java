package im.dadoo.turtle.dto;

public class StockDto {

  private String code;

  private String name;

  private String time;

  private double price;

  private double max20Price;

  private double min10Price;

  private double atr20;

  private double n20;

  private double atr250;

  private double n250;

  private int status;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("StockDto{");
    sb.append("code='").append(code).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", time='").append(time).append('\'');
    sb.append(", price=").append(price);
    sb.append(", max20Price=").append(max20Price);
    sb.append(", min10Price=").append(min10Price);
    sb.append(", atr20=").append(atr20);
    sb.append(", n20=").append(n20);
    sb.append(", atr250=").append(atr250);
    sb.append(", n250=").append(n250);
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

  public double getMax20Price() {
    return max20Price;
  }

  public void setMax20Price(double max20Price) {
    this.max20Price = max20Price;
  }

  public double getMin10Price() {
    return min10Price;
  }

  public void setMin10Price(double min10Price) {
    this.min10Price = min10Price;
  }

  public double getAtr20() {
    return atr20;
  }

  public void setAtr20(double atr20) {
    this.atr20 = atr20;
  }

  public double getN20() {
    return n20;
  }

  public void setN20(double n20) {
    this.n20 = n20;
  }

  public double getAtr250() {
    return atr250;
  }

  public void setAtr250(double atr250) {
    this.atr250 = atr250;
  }

  public double getN250() {
    return n250;
  }

  public void setN250(double n250) {
    this.n250 = n250;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
