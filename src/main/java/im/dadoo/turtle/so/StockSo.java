package im.dadoo.turtle.so;

import com.google.common.base.Joiner;
import im.dadoo.turtle.bo.ConverterBo;
import im.dadoo.turtle.co.QuoteCo;
import im.dadoo.turtle.dto.QuoteDto;
import im.dadoo.turtle.dto.StockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockSo {

  private static final Logger ELOGGER = LoggerFactory.getLogger(Exception.class);

  @Resource
  private List<StockDto> stockCache;

  @Resource
  private ConverterBo converterBo;

  @Resource
  private QuoteCo quoteCo;

  public void refresh() {
    try {
      List<String> codes = this.stockCache.stream().map(stockDto -> stockDto.getCode()).collect(Collectors.toList());
      String lines = this.quoteCo.quote(Joiner.on(",").join(codes));
      List<QuoteDto> quoteDtos = this.converterBo.toQuoteDtos(lines);
      for (int i = 0; i < stockCache.size(); i++) {
        StockDto stockDto = stockCache.get(i);
        QuoteDto quoteDto = quoteDtos.get(i);
        stockDto.setName(quoteDto.getName());
        stockDto.setTime(quoteDto.getTime());
        stockDto.setPrice(quoteDto.getPrice());
      }
    } catch (Exception e) {
      ELOGGER.error("更新股票信息失败", e);
    }

  }

  public List<StockDto> list() {
    return this.stockCache;
  }

}
