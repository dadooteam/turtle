package im.dadoo.turtle.controller;

import im.dadoo.turtle.bo.ConverterBo;
import im.dadoo.turtle.co.QuoteCo;
import im.dadoo.turtle.constant.Constant;
import im.dadoo.turtle.dto.QuoteDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author codekitten
 */
@Controller
public class HelloController {

  @Resource
  private QuoteCo quoteCo;

  @Resource
  private ConverterBo converterBo;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  @ResponseBody
  public Object hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "hello";
  }
}
