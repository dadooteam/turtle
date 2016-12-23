package im.dadoo.turtle.util;

/**
 * Created by codekitten on 2016/12/23.
 */
public final class StockUtil {

  private StockUtil() {}

  public static String toStatusContent(int status) {
    if (status == 1) {
      return "做多";
    } else if (status == -1) {
      return "做空";
    }
    return null;
  }
}
