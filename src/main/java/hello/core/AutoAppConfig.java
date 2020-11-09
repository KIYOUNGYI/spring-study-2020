package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//예제를 돌리기 위해서 넣어둠. AppConfig Configuration 들어가면 @component 달려있음. 고로 이녀석도 검색 대상이 되면서 충돌나요
//컨피규레이션 어노테이션 달린건 컴포넌트 스캔 대상에서 제외시킬거야
//실무에서는 이러진 않겠죠. 이렇게 한 것은 예제코드를 살리기 위해서!!!
public class AutoAppConfig {

  /**
   * 아래 메소드 등록하고 AutoAppConfigTest.class 돌릴 때
   * DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -
   * Overriding bean definition for bean 'memoryMemberRepository' with a different definition:
   * replacing [Generic bean: class [hello.core.member.MemoryMemberRepository]; scope=singleton; abstract=false; lazyInit=null;
   * =================================================================================================
   * 스프링 부트 가동시
   * The bean 'memoryMemberRepository', defined in class path resource
   * [hello/core/AutoAppConfig.class], could not be registered.
   * A bean with that name has already been defined in file
   * [/Users/glowdayz/Documents/personal-study/core/out/production/classes/hello/core/member/MemoryMemberRepository.class]
   * and overriding is disabled.
   */
//  @Bean(name = "memoryMemberRepository")
//  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }
}
