package im.dadoo.turtle.context;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import im.dadoo.turtle.dto.QuoteDto;
import org.apache.http.client.fluent.Executor;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy
@PropertySource("file:server.properties")
@ComponentScan({"im.dadoo"})
public class TurtleContext extends WebMvcConfigurerAdapter {

  @Bean(destroyMethod = "shutdown")
  public ExecutorService scheduler() {
    return Executors.newScheduledThreadPool(10);
  }

  @Bean(destroyMethod = "shutdown")
  public ExecutorService executor() {
    return Executors.newCachedThreadPool();
  }

  @Bean
  public Executor httpExecutor() {
    return Executor.newInstance();
  }

  /**
   * response body 转换器
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new StringHttpMessageConverter());
    converters.add(new GsonHttpMessageConverter());
  }

}
