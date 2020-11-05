package hello.core.member;

public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  // dbMemberRepository() <- ㅇㅣ런 식으로 넣어주는건 ocp 위반

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
