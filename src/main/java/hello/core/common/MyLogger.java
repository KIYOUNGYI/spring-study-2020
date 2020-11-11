package hello.core.common;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

  private String uuid;
  private String requestURL;

  public void setRequestURL(String requestURL) {
    this.requestURL = requestURL;
  }

  public void log(String message) {
    System.out.println("[ " + uuid + " ] " + "[ " + requestURL + "] " + message);
  }

  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString(); // 진짜 안 겹침
    System.out.println("[ " + uuid + " ] request scope bean create:" + this);
  }

  @PreDestroy//이건 호출되요~~ 그 프로토타입이랑 다르쥬?
  public void close() {
    System.out.println("[ " + uuid + " ] request scope bean close:" + this);
  }
}
