package hello.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

  @Test
  @DisplayName("lifeCycleTest_널찍힘")
  public void lifeCycleTest_널찍힘() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);//부모 인터페이스는 자식 인터페이스를 담을 수 있다.
    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close();
  }
  // 생성자 호출, url = null
  // connect = null
  // call : null + message = 초기화 연결 메시지

  @Test
  @DisplayName("lifeCycleTest_인터페이스_사용_but_스프링에_종속적")
  public void lifeCycleTest_인터페이스_사용_but_스프링에_종속적() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV2.class);
    NetworkClientV2 client = ac.getBean(NetworkClientV2.class);
    ac.close();
  }
//  생성자 호출, url = null
//  NetworkClient.afterPropertiesSet
//  connect: http://hello-spring.dev
//  call: http://hello-spring.dev message = 초기화 연결 메시지
//      13:24:49.043 [main] DEBUG
//  org.springframework.context.annotation.AnnotationConfigApplicationContext -
//  Closing NetworkClient.destroy
//  close + http://hello-spring.dev

  @Test
  @DisplayName("lifeCycleTest_빈_속성_설정")
  public void lifeCycleTest_빈_속성_설정() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV3.class);
    NetworkClientV3 client = ac.getBean(NetworkClientV3.class);
    ac.close();
  }
//  생성자 호출, url = null
//  NetworkClientV3.init
//  connect : http://hello-spring.dev
//  call: http://hello-spring.dev message = 초기화 연결 메시지
//      10:00:07.143 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@78a2da20, started on Tue Nov 10 10:00:06 KST 2020
//  NetworkClient.close
//  close + http://hello-spring.dev

  @Test
  @DisplayName("lifeCycleTest_post_construct_pre_destroy_사용")
  public void lifeCycleTest_post_construct_pre_destroy_사용() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV4.class);
    NetworkClientV4 client = ac.getBean(NetworkClientV4.class);
    ac.close();
  }


  @Configuration
  static class LifeCycleConfig {

    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();//요기서 프린트 찍히고, 커넥트 호출하고 , 초기화 메시지 보내주는데, url 은 널값이 찍힌다.
      networkClient.setUrl("http://hello-spring.dev");//사실 이걸 생성자에 넣어도 되긴 하는데, 설정이 객체 생성 후 또 들어올 수 있거든요 ~
      return networkClient;
    }
  }

  @Configuration
  static class LifeCycleConfigV2 {

    @Bean
    public NetworkClientV2 networkClientUsingInterface() {
      NetworkClientV2 nc = new NetworkClientV2();
      nc.setUrl("http://hello-spring.dev");
      return nc;
    }
  }

  @Configuration
  static class LifeCycleConfigV3 {

    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClientV3 networkClientV3() {
      NetworkClientV3 nc = new NetworkClientV3();
      nc.setUrl("http://hello-spring.dev");
      return nc;
    }
  }

  @Configuration
  static class LifeCycleConfigV4 {

    @Bean
    public NetworkClientV4 networkClientV4() {
      NetworkClientV4 nc = new NetworkClientV4();
      nc.setUrl("http://hello-spring.dev");
      return nc;
    }
  }
}
