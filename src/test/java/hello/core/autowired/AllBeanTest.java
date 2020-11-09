package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);
    DiscountService ds = ac.getBean(DiscountService.class);
    Member member = new Member(1l, "userA", Grade.VIP);
    int discountPrice = ds.discount(member, 10000, "fixDiscountPolicy");

    assertThat(ds).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000);

    int discountPrice2 = ds.discount(member, 20000, "rateDiscountPolicy");
    assertThat(discountPrice2).isEqualTo(2000);
  }

  //  @RequiredArgsConstructor
  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policyList;

    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
      this.policyMap = policyMap;
      this.policyList = policyList;
      System.out.println("policyMap => " + policyMap.toString());
      System.out.println("policyList => " + policyList.toString());
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }
}
