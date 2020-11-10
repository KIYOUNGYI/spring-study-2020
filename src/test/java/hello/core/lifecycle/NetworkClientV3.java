package hello.core.lifecycle;

/**
 * 설정 정보를 사용하도록 변경
 */
public class NetworkClientV3 {

  private String url;

  public NetworkClientV3() {
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

  public void init() {
    System.out.println("NetworkClientV3.init");
    connect();
    call("초기화 연결 메시지");
  }

  public void close() {
    System.out.println("NetworkClientV3.close");
    disConnect();
  }
}
