package im.dadoo.turtle.bo;

import com.google.common.base.Splitter;
import im.dadoo.turtle.constant.Constant;
import im.dadoo.turtle.dto.QuoteDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ConverterBo {

  private static final Pattern SINA_PATTERN = Pattern.compile("var \\w+=\"(\\S+)\"\\;?");

  public QuoteDto toQuoteDto(String code, int market, String sina) {
    QuoteDto r = null;
    if (StringUtils.isNotBlank(sina)) {
      sina = StringUtils.strip(sina);
      r = new QuoteDto();
      r.setCode(code);
      r.setMarket(market);
      if (market == Constant.MARKET_CN) {
        Matcher matcher = SINA_PATTERN.matcher(sina);
        if (matcher.matches()) {
          String data = matcher.group(1);
          List<String> temp = Splitter.on(",").splitToList(data);
          r.setPrice(NumberUtils.toDouble(temp.get(3)));
          r.setTime(String.format("%sT%s", temp.get(temp.size() - 3), temp.get(temp.size() - 2)));
        }
      }
    }
    return r;
  }

  public List<QuoteDto> toQuoteDtos(List<String> codes, List<Integer> markets, String sina) {
    List<QuoteDto> r = new ArrayList<>();
    if (StringUtils.isNotBlank(sina)) {
      List<String> datas = Splitter.on(";").splitToList(sina);
      for (int i = 0; i < codes.size(); i++) {
        r.add(this.toQuoteDto(codes.get(i), markets.get(i), datas.get(i)));
      }
    }
    return r;
  }
}
