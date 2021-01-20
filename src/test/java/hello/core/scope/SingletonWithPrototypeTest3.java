package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest3 {

  @Test
  void providerTest() {
    AnnotationConfigApplicationContext ac = new
        AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean c1 = ac.getBean(ClientBean.class);
    int count1 = c1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean c2 = ac.getBean(ClientBean.class);
    int count2 = c2.logic();
    assertThat(count2).isEqualTo(1);
  }


  @Scope("singleton")
  static class ClientBean {

    private final Provider<PrototypeBean> prototypeBeanProvider;

    @Autowired
    public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
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
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

}
