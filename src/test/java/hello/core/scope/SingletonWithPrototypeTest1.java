package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(2);

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  void providerTest() {
    AnnotationConfigApplicationContext ac = new
        AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    ClientBean clientBean1 = ac.getBean(ClientBean.class);

    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);
    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    System.out.println("clientBean1="+clientBean1);
    System.out.println("clientBean2="+clientBean2);
    assertThat(count2).isEqualTo(1);
  }


  @Test
  @DisplayName("싱글톤 빈에서 프로토타입 빈 사용시 문제점 노출")
  void singletonClientUsePrototypeProblem() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanProblem.class, PrototypeBean.class);
    ClientBeanProblem clientBeanProblem = ac.getBean(ClientBeanProblem.class);
    int count1 = clientBeanProblem.logic();
    assertThat(count1).isEqualTo(1);
    ClientBeanProblem clientBeanProblem2 = ac.getBean(ClientBeanProblem.class);
    int count2 = clientBeanProblem2.logic();
    assertThat(count2).isEqualTo(2);
  }


  @Test
  @DisplayName("싱글톤 빈에서 프로토타입 빈 사용")
  void singletonClientUsePrototypeIgnorant() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanIgnorant.class, PrototypeBean.class);
    ClientBeanIgnorant acBean = ac.getBean(ClientBeanIgnorant.class);
    int count1 = acBean.logic();
    assertThat(count1).isEqualTo(1);
    ClientBeanIgnorant acBean2 = ac.getBean(ClientBeanIgnorant.class);
    int count2 = acBean2.logic();
    assertThat(count2).isEqualTo(1);
  }


  @Test
  @DisplayName("싱글톤 빈에서 프로토타입 빈 사용(-> 해결방법 : 퀄리파이어 사용)")
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);
    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    System.out.println("clientBean1 = " + clientBean1);
    System.out.println("clientBEan2 = " + clientBean2);
    assertThat(clientBean1).isSameAs(clientBean2);
    assertThat(count2).isEqualTo(1);
  }

  @Scope("singleton")
  static class ClientBeanIgnorant {

    @Autowired
    ApplicationContext applicationContext;


    public int logic() {
      PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }

  }


  @Scope("singleton")
  static class ClientBeanProblem {

    private final PrototypeBean prototypeBean;//생성시점에 주입,

    @Autowired
    public ClientBeanProblem(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }

  }


  @Scope("singleton")
  static class ClientBean {

    private ObjectProvider<PrototypeBean> prototypeBeanProvider;

    @Autowired
    public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }


  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count += 1;
    }

    public int getCount() {
      return count;
    }

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
