package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *   @PostConstruct,  @PreDestroy
 */
public class NetworkClientV4 {

  private String url;

  public NetworkClientV4() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void connect() {
    System.out.println("connect : " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + " message = " + message);
  }

  //서비스 종료시 호출
  public void disConnect() {
    System.out.println("close + " + url);
  }

  @PostConstruct
  public void init() {
    System.out.println("NetworkClientV4.init");
    connect();
    call("초기화 연결 메시지");
  }

  @PreDestroy
  public void close() {
    System.out.println("NetworkClientV4.close");
    disConnect();
  }
}
