package hello.core.config;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 메소드 명과 리턴타입으로 역할을 한 눈에 보게끔 하고
 * 구현 클래스가 한눈에 보이죵 :)
 */
public class AppConfig {
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }


  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

}
