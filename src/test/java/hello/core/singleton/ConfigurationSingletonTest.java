package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {


  /**
   * //expected //call AppConfig.memberService //call AppConfig.memberRepository //call AppConfig.memberRepository //call AppConfig.orderService //call AppConfig.memberRepository
   * <p>
   * <p>
   * //real call AppConfig.memberService call AppConfig.memberRepository call AppConfig.orderService
   */
  @Test
  public void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository m1 = memberService.getMemberRepository();
    MemberRepository m2 = orderService.getMemberRepository();

    System.out.println("memberService -> memberRepository = " + m1.toString());
    System.out.println("orderService -> memberRepository = " + m2.toString());
    System.out.println("memberRepository = " + memberRepository);
    Assertions.assertThat(m1).isSameAs(m2).isSameAs(memberRepository);

  }


  @Test
  void congiutrationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());//이건 내가 만든 클래스가 아니다!!!!
  }


}
