package hello.core.config;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

}
