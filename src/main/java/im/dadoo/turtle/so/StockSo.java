package im.dadoo.turtle.so;

import com.google.common.base.Joiner;
import im.dadoo.turtle.bo.ConverterBo;
import im.dadoo.turtle.co.QuoteCo;
import im.dadoo.turtle.dao.HistoryDao;
import im.dadoo.turtle.dto.QuoteDto;
import im.dadoo.turtle.dto.StockDto;
import im.dadoo.turtle.po.HistoryPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StockSo {

  private static final Logger ELOGGER = LoggerFactory.getLogger(Exception.class);

  @Resource
  private List<StockDto> stockCache;

  @Resource
  private ConverterBo converterBo;

  @Resource
  private HistoryDao historyDao;

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


        List<HistoryPo> history20Pos = this.historyDao.listByCode(stockDto.getCode(), 21L);
        history20Pos.remove(0); //去掉当日数据
        stockDto.setMax20Price(history20Pos.stream().max(Comparator.comparing(po -> po.getClose())).get().getClose());

        stockDto.setAtr20(history20Pos.stream().map(po -> po.getAtr()).reduce((acc, elem) -> acc + elem).get() / history20Pos.size());
        stockDto.setN20(history20Pos.stream().map(po -> po.getN()).reduce((acc, elem) -> acc + elem).get() / history20Pos.size());

        List<HistoryPo> history10Pos = this.historyDao.listByCode(stockDto.getCode(), 11L);
        history10Pos.remove(0); //去掉当日数据
        stockDto.setMin10Price(history10Pos.stream().min(Comparator.comparing(po -> po.getClose())).get().getClose());

        List<HistoryPo> history250Pos = this.historyDao.listByCode(stockDto.getCode(), 251L);
        history250Pos.remove(0); //去掉当日数据
        stockDto.setAtr250(history250Pos.stream().map(po -> po.getAtr()).reduce((acc, elem) -> acc + elem).get() / history250Pos.size());
        stockDto.setN250(history250Pos.stream().map(po -> po.getN()).reduce((acc, elem) -> acc + elem).get() / history250Pos.size());

        if (stockDto.getPrice() > stockDto.getMax20Price()) {
          stockDto.setStatus(1);
        } else if (stockDto.getPrice() < stockDto.getMin10Price()) {
          stockDto.setStatus(-1);
        } else {
          stockDto.setStatus(history10Pos.get(0).getStatus());
        }

        //update t_history
        HistoryPo po = new HistoryPo();
        po.setCode(stockDto.getCode());
        po.setDate(quoteDto.getTime().substring(0, 10));
        po.setOpen(quoteDto.getOpen());
        po.setClose(quoteDto.getPrice());
        po.setHigh(quoteDto.getHigh());
        po.setLow(quoteDto.getLow());
        Stream<Double> maxstream = Stream.of(quoteDto.getHigh(), quoteDto.getLow(), quoteDto.getLastClose());
        Stream<Double> minstream = Stream.of(quoteDto.getHigh(), quoteDto.getLow(), quoteDto.getLastClose());
        po.setAtr(maxstream.max(Double::compareTo).get() - minstream.min(Double::compareTo).get());
        po.setN(100 * po.getAtr() / quoteDto.getLastClose());
        po.setStatus(stockDto.getStatus());

        if (null != this.historyDao.findByCodeAndDate(po.getCode(), po.getDate())) {
          this.historyDao.updateByCodeAndDate(po);
        } else {
          this.historyDao.insert(po);
        }
      }
    } catch (Exception e) {
      ELOGGER.error("更新股票信息失败", e);
    }

  }

  public List<StockDto> list() {
    return this.stockCache;
  }

}
