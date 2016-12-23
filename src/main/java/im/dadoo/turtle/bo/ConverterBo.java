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

  public QuoteDto toQuoteDto(String line) {
    QuoteDto r = null;
    if (StringUtils.isNotBlank(line)) {
      line = StringUtils.strip(line);
      r = new QuoteDto();
      Matcher matcher = SINA_PATTERN.matcher(line);
      if (matcher.matches()) {
        String data = matcher.group(1);
        List<String> temp = Splitter.on(",").splitToList(data);
        r.setName(temp.get(0));
        r.setPrice(NumberUtils.toDouble(temp.get(3)));
        r.setTime(String.format("%sT%s", temp.get(temp.size() - 3), temp.get(temp.size() - 2)));
      }
    }
    return r;
  }

  public List<QuoteDto> toQuoteDtos(String lines) {
    List<QuoteDto> r = new ArrayList<>();
    if (StringUtils.isNotBlank(lines)) {
      List<String> list = Splitter.on(";").splitToList(lines);
      for (int i = 0; i < list.size(); i++) {
        r.add(this.toQuoteDto(list.get(i)));
      }
    }
    return r;
  }
}
