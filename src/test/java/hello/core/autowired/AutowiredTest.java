package hello.core.autowired;


import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

  @Test
  void autowiredOption() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    ac.getBean(TestBean.class);
  }

  static class TestBean {

    @Autowired(required = false)
    public void setNoBean1(Member member) {//이녀석 자체가 호출이 안됨.
      System.out.println("nobean1 = " + member);
    }

    @Autowired(required = false)
    public void setNoBean2(@Nullable Member member) {
      System.out.println("nobean2 = " + member);
    }

    @Autowired(required = false)
    public void setNoBean3(Optional<Member> member) {
      System.out.println("nobean3 = " + member);
    }
  }
}
