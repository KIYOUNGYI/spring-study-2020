package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.config.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  /**
   * 스프링이 자체적으로 확장하기 위해 등록된 빈도 출력이 된다!
   */
  @Test
  @DisplayName("모든 빈 출력하기")
  void findBeans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = ac.getBean(beanDefinitionName);//오브젝트로 나오는 이유 -> 리턴이 뭐가 될 지 모르니까!!!
      System.out.println("name->" + bean.toString());
    }
  }


  /**
   *
   */
  @Test
  @DisplayName("애플리케이션 빈 출력")
  void findApplicationBeans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {

//      BeanDefinition.ROLE_INFRASTRUCTURE <- 스프링이 자체적으로 애플리케이션 컨텍스트에 등록하는 것
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);//오브젝트로 나오는 이유 -> 리턴이 뭐가 될 지 모르니까!!!
        System.out.println("name -> " + bean);
      }
    }
  }


  @Test
  @DisplayName("빈 이름으로 조회")
  void findByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("이름 없이 타입으로만 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByName2() {
    MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
    //리턴해중는 인스턴스 타입을 보기 때문에 괜찮다! 물론 그렇긴 한데, 구체적으로 적는것은 좋지 않다!
    //항상 역할과 구현은 구분해야 한다. 그런데 이 테스트케이스 같은 경우에는 구현에 의존한거지.
    Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  //여러분들 테스트는 항상 실패 테스트를 만드셔야 합니다!!
  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByNameX() {
    assertThrows(NoSuchBeanDefinitionException.class,
        () -> ac.getBean("xxxxx", MemberService.class));
  }
}
