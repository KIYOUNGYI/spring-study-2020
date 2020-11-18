package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    System.out.println("memberService -> memberRepository = " + m2.toString());
    System.out.println("memberRepository = " + memberRepository);
    Assertions.assertThat(m1).isSameAs(m2).isSameAs(memberRepository);

  }


  @Test
  void congiutrationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());//이건 내가 만든 클래스가 아니다!!!!
  }

  //또하나, @Configuration 어노테이션이 어떻게든 싱글톤을 보장해준다는 걸 알 수 있음.
  @Test
  @DisplayName("LocalAppConfig2 에 configuration 어노테이션을 달면 싱글톤이 지켜지고, 빼면 싱글톤이 깨진다. 생성자에 시스아웃 찍어서 비교했는데 지움.")
  void experiment() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(LocalAppConfig2.class);
    LocalAppConfig2 bean = ac.getBean(LocalAppConfig2.class);
    System.out.println("bean = " + bean);
  }

  @Test
  @DisplayName("")
  void experiment002() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
    memberService.getMemberRepository();
    OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
    orderService.getMemberRepository();

    Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(orderService.getMemberRepository());
  }

  @Configuration
  static class LocalAppConfig2 {

    @Bean
    public MemberService memberService() {
      System.out.println("call memberService");
      return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
      return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
      System.out.println("call orderService");
      return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
  }

}
