package im.dadoo.turtle.controller;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import im.dadoo.turtle.dto.StockDto;
import im.dadoo.turtle.so.StockSo;
import im.dadoo.turtle.util.StockUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StockController {

  @Resource
  private StockSo stockSo;

  @RequestMapping(value = "/stocks", method = RequestMethod.GET)
  @ResponseBody
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "*");
    response.setContentType("text/html;charset=UTF-8");
    List<StockDto> stockDtos =  this.stockSo.list();
    String htmlTemplate = "<html><head><meta charset=\"UTF-8\"></head><body><table border=\"1\"><thead><tr><th>股票代码</th><th>名称</th><th>时间戳</th><th>当前价格</th><th>max20</th><th>min10</th><th>20日atr值</th><th>20日n值</th><th>状态</th></tr></thead><tbody>%s</tbody></table></body></html>";
    String tableTemplate = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%.2f</td><td>%s</td></tr>";

    List<String> tables = stockDtos.stream().map(stockDto ->
      String.format(tableTemplate, stockDto.getCode(), stockDto.getName(), stockDto.getTime(), stockDto.getPrice(), stockDto.getMax20Price(), stockDto.getMin10Price(), stockDto.getAtr20(), stockDto.getN20(), StockUtil.toStatusContent(stockDto.getStatus()))
    ).collect(Collectors.toList());
    String r = String.format(htmlTemplate, Joiner.on("").join(tables));
    return r;
  }
}
