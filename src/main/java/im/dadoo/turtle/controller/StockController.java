package im.dadoo.turtle.controller;

import im.dadoo.turtle.so.StockSo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StockController {

  @Resource
  private StockSo stockSo;

  @RequestMapping(value = "/stocks", method = RequestMethod.GET)
  @ResponseBody
  public Object list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return this.stockSo.list();
  }
}
