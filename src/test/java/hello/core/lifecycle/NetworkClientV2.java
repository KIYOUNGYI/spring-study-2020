package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClientV2 implements InitializingBean, DisposableBean {

  private String url;

  public NetworkClientV2() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void connect() {
    System.out.println("connect : " + url);
  }

  public void call(String message) {
    System.out.println("connect : " + url + " message : " + message);
  }

  public void disConnect() {
    System.out.println("close : " + url);
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("NetworkClientV2.destroy");
    disConnect();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("NetworkClientV2.afterPropertiesSet");
    connect();
    call("초기화 연결 메시지");
  }
}
