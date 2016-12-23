package im.dadoo.turtle.dto;

public class QuoteDto {

  private String name;

  private String time;

  private double price;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("QuoteDto{");
    sb.append("name='").append(name).append('\'');
    sb.append(", time='").append(time).append('\'');
    sb.append(", price=").append(price);
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
}
