package hello.core.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//클래스 레벨에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
//MyIncludeComponent 가 붙은 건 컴포넌트 스캔에 추가할 것이여~
}
