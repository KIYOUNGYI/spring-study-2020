package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수 di 컨테이너")
  void 순수() {
    AppConfig appConfig = new AppConfig();
    MemberService m1 = appConfig.memberService();
    MemberService m2 = appConfig.memberService();

    System.out.println("m1:" + m1.toString());
    System.out.println("m2:" + m2.toString());

    assertThat(m1).isNotSameAs(m2);

  }


  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  void 싱글톤_객체_테스트() {
    SingletonService sg1 = SingletonService.getInstance();
    SingletonService sg2 = SingletonService.getInstance();
    System.out.println(sg1.toString());
    System.out.println(sg2.toString());
    assertThat(sg1).isSameAs(sg2);
  }

  @Test
  @DisplayName("스프링 컨테이너 사용")
  void name() {
    AppConfig appConfig = new AppConfig();
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService m1 = ac.getBean("memberService", MemberService.class);
    MemberService m2 = ac.getBean("memberService", MemberService.class);

    System.out.println("m1:" + m1.toString());
    System.out.println("m2:" + m2.toString());

    assertThat(m1).isSameAs(m2);
  }
}
