package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.config.DiscountPolicyConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 전략패턴
 */
public class AllBeanTest {

  @Test
  void 아래메소드와_비교해보라고_임의로_만든_메소드() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class);
    //policyMap => {}
    //policyList => []
  }

  @Test
  void findAllBean() {
//    ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);
//    getAppBeans(ac);

    DiscountService ds = ac.getBean(DiscountService.class);
    Member member = new Member(1l, "userA", Grade.VIP);
    int discountPrice = ds.discount(member, 10000, "fixDiscountPolicy");

    assertThat(ds).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000);

    int discountPrice2 = ds.discount(member, 20000, "rateDiscountPolicy");
    assertThat(discountPrice2).isEqualTo(2000);
  }

  @Test
  void findAllBeanByConfig() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountPolicyConfig.class, DiscountServiceNoPoly.class);
//    getAppBeans(ac);
//
//    DiscountPolicyConfig dc = ac.getBean(DiscountPolicyConfig.class);
////    DiscountPolicy fixDiscountPolicy = dc.fixDiscountPolicy();
////    DiscountPolicy rateDiscountPolicy = dc.rateDiscountPolicy();
//
//    DiscountServiceNoPoly dsNoPoly = ac.getBean(DiscountServiceNoPoly.class);
//
//    Member member = new Member(1l, "userA", Grade.VIP);
//    int discountPrice = dsNoPoly.discount(member, 10000, fixDiscountPolicy);
//    assertThat(dsNoPoly).isInstanceOf(DiscountServiceNoPoly.class);
//    assertThat(discountPrice).isEqualTo(1000);

//    int discountPrice2 = dsNoPoly.discount(member, 20000, rateDiscountPolicy);
//    assertThat(discountPrice2).isEqualTo(2000);
  }


  private void getAppBeans(AnnotationConfigApplicationContext ac) {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {

      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);//오브젝트로 나오는 이유 -> 리턴이 뭐가 될 지 모르니까!!!
        System.out.println("name -> " + bean);
      }
    }
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

      System.out.println("discountCode = " + discountCode);
      System.out.println("discountPolicy = " + discountPolicy);

      return discountPolicy.discount(member, price);
    }
  }

  static class DiscountServiceNoPoly {


    public int discount(Member member, int price, DiscountPolicy policy) {
      return policy.discount(member, price);
    }

  }

}

// signleton 아래 것들을 만듬
// internalEventListenerProcessor
// internalEventListenerFactory
// internalAutowiredAnnotationProcessor
// internalCommonAnnotationProcessor
// Autowiring by type from bean name 'allBeanTest.DiscountService' via constructor to bean named 'fixDiscountPolicy'
// Autowiring by type from bean name 'allBeanTest.DiscountService' via constructor to bean named 'rateDiscountPolicy'
// policyMap => {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@54c5a2ff, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@6d4d66d2}
// policyList => [hello.core.discount.FixDiscountPolicy@54c5a2ff, hello.core.discount.RateDiscountPolicy@6d4d66d2]
// autoAppConfig
// Creating shared instance of singleton bean 'memberServiceImpl'
// Creating shared instance of singleton bean 'memoryMemberRepository'
// Autowiring by type from bean name 'memberServiceImpl' via constructor to bean named 'memoryMemberRepository'
// Creating shared instance of singleton bean 'orderServiceImpl'
// Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'memoryMemberRepository'
// Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'rateDiscountPolicy'

//OrderServiceImpl.OrderServiceImpl
//name -> hello.core.autowired.AllBeanTest$DiscountService@6e9a5ed8
//name -> hello.core.AutoAppConfig$$EnhancerBySpringCGLIB$$34b6497c@7e057f43
//name -> hello.core.discount.FixDiscountPolicy@54c5a2ff
//name -> hello.core.discount.RateDiscountPolicy@6d4d66d2
//name -> hello.core.member.MemberServiceImpl@6c284af
//name -> hello.core.member.MemoryMemberRepository@5890e879
//name -> hello.core.order.OrderServiceImpl@6440112d