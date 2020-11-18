package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AutoAppConfigTest {

  @Test
  void basicScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  @DisplayName("여러 번 sout 찍은게 출력되어야 한다")
  void baseScan2() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(LocalAppConfig.class);
    LocalAppConfig bean = ac.getBean(LocalAppConfig.class);
    System.out.println("bean = " + bean);
  }

  static class LocalAppConfig {

    @Bean
    public MemberService memberService() {
      System.out.println("call AppConfig.memberService");
      return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
      System.out.println("call AppConfig.memberRepository");
      return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
      System.out.println("call AppConfig.orderService");
      return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
  }
}
