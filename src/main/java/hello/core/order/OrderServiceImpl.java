package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

//  @Autowired
//  public void setMemberRepository(MemberRepository memberRepository) {
//    System.out.println("OrderServiceImpl.setMemberRepository");
//    this.memberRepository = memberRepository;
//  }
//
//  @Autowired
//  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//    System.out.println("OrderServiceImpl.setDiscountPolicy");
//    this.discountPolicy = discountPolicy;
//  }

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
    System.out.println("OrderServiceImpl.OrderServiceImpl");
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {

    Member member = memberRepository.findById(memberId);

    int discount = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discount);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
