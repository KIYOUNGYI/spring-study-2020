package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class ScopeTest {

  /**
   * bean 초기화 메서드를 실행하고,
   * 같은 인스턴스의 빈을 조회하고,
   * 종료 메서드까지 정상 호출 된 것을 확인할 수 있다.
   */
  @Test
  public void singletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean sb1 = ac.getBean(SingletonBean.class);
    SingletonBean sb2 = ac.getBean(SingletonBean.class);
    System.out.println("s1 = " + sb1.toString());
    System.out.println("s2 = " + sb2.toString());
    assertThat(sb1).isSameAs(sb2);
    assertThat(sb1).isEqualTo(sb2);

    ac.close();//이것까지 해줘야 destroy 호출!
  }

  @Test
  public void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    System.out.println("find prototypeBean1");//프로토타입빈을 조회하기 직전에 생성된다고 했자나요.
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
    prototypeBean1.destroy();
    prototypeBean2.destroy();
    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }

  @Scope("prototype")
  @Component//테스트에서 지정해줬기 때문에 컴포넌트 어노테이션 없어도 컴포넌트처럼 동작한다~
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }


}
