package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy//(위에 퀄리파이어보다 좀 더 깔끔하지 -> 실수 확률을 줄이지 ~ )
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

  private int discontPercent = 10;

  @Override
  public int discount(Member member, int price) {

    if (member.getGrade() == Grade.VIP) {
      return price * (discontPercent) / 100;
    }

    return 0;
  }
}
