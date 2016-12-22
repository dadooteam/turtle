package im.dadoo.turtle.co;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;

@Component
public class QuoteCo {

  private static final String ROOT = "hq.sinajs.cn";

  @Resource
  private Executor httpExecutor;

  public String quote(String codes) throws Exception {
    URI uri = new URIBuilder("http://" + ROOT).setPath(String.format("/list=%s", codes))
        .setCharset(Charsets.UTF_8).build();
    HttpResponse res = this.httpExecutor.execute(Request.Get(uri)).returnResponse();
    return EntityUtils.toString(res.getEntity(), Charsets.UTF_8);
  }
}
