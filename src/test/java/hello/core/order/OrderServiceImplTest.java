package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

  /**
   * 생성자 주입을 사용해야 하는 이유 ~~~
   */
  @Test
  void createOrder() {
    MemberRepository mr = new MemoryMemberRepository();
    MemberService ms = new MemberServiceImpl(mr);
    DiscountPolicy dp = new FixDiscountPolicy();
    ms.join(new Member(1l, "hello", Grade.VIP));
    OrderServiceImpl orderService = new OrderServiceImpl(mr, dp);
    Order itemA = orderService.createOrder(1l, "itemA", 10000);
    int discountPrice = itemA.getDiscountPrice();
    Assertions.assertThat(discountPrice).isEqualTo(1000);
  }
}
