package hello.core;

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

}
