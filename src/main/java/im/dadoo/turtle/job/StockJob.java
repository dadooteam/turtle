package im.dadoo.turtle.job;

import im.dadoo.turtle.so.StockSo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StockJob {

  private static final Logger MLOGGER = LoggerFactory.getLogger(StockJob.class);

  @Resource
  private StockSo stockSo;

  @Scheduled(cron = "0 * * * * ?")
  public void execute() {
    MLOGGER.info("刷新股票缓存start");
    this.stockSo.refresh();
    MLOGGER.info("刷新股票缓存end");
  }
}
