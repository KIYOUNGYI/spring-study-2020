package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

  @Test
  @DisplayName("이렇게 되면 망하는거야~~~, 이 예제는 단순한 건데 멀티스레드 환경에선 더 복잡하겠지. (멀티스레드는 어디서 공부하는게 효율적일까) ")
  public void satefulServiceSingleton_망하는_케이스() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService s1 = ac.getBean(StatefulService.class);
    StatefulService s2 = ac.getBean(StatefulService.class);

    s1.Order("userA", 10000);
    s2.Order("userB", 20000);

    //ThreadA: 사용자a 주문 금액 조회
    int price = s1.getPrice();
    System.out.println("price : " + price);
    Assertions.assertThat(s1.getPrice()).isEqualTo(20000);
  }


  @Test
  @DisplayName("필드를 없앴소~")
  public void satefulServiceSingleton_보완한_케이스() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulServiceWIthoutField s1 = ac.getBean(StatefulServiceWIthoutField.class);
    StatefulServiceWIthoutField s2 = ac.getBean(StatefulServiceWIthoutField.class);

    int price1 = s1.Order("userA", 10000);
    int price2 = s2.Order("userB", 20000);

    //ThreadA: 사용자a 주문 금액 조회

    System.out.println("price : " + price1);
    Assertions.assertThat(price1).isEqualTo(10000);
    Assertions.assertThat(price2).isEqualTo(20000);
  }


//  @Configuration
  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }

    @Bean
    public StatefulServiceWIthoutField statefulServiceWIthoutField() {
      return new StatefulServiceWIthoutField();
    }

  }
}