package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수 di 컨테이너")
  void 순수(){
    AppConfig appConfig = new AppConfig();
    MemberService m1 = appConfig.memberService();
    MemberService m2 = appConfig.memberService();

    System.out.println("m1:"+m1.toString());
    System.out.println("m2:"+m2.toString());

    Assertions.assertThat(m1).isNotSameAs(m2);

  }
}
