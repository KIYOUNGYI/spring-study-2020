package hello.core.beanfind;

import hello.core.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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


}
