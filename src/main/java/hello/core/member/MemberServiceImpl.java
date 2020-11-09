package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

//  @Autowired // ac.getBean(MemberRepository.class) 와 같은것임
//  public MemberServiceImpl(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
