package hello.core.discount;

import static org.assertj.core.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
  void vip_o() {
    //given
    Member member = new Member(1l, "memberVIP", Grade.VIP);
    //when
    int discount = discountPolicy.discount(member, 10000);
    //then
    assertThat(discount).isEqualTo(1000);
  }

  @Test
  @DisplayName("VIP 가 아니면 10% 할인이 되면 안된다.")
  void vip_x() {
    //given
    Member member = new Member(1l, "memberVIP", Grade.BASIC);
    //when
    int discount = discountPolicy.discount(member, 10000);
    //then
    assertThat(discount).isNotEqualTo(1000);
  }

}