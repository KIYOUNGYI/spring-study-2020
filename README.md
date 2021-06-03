# 스프링 핵심원리

# 객체 지향 설계와 스프링

## 역사 
- EJB 시절 -> 선배님들, 엄청 힘들었다고 한다. 컨테이너 띄우는데 겁나 거릴고
- 로드 존슨이 EJB 보다 편히 쓰려고 스프링 만들었다 보며 됨.
- 하이버네이트도 마찬가지 (게빈 킹)
- 불편해서 생겨났다 정도로 이해하면 될듯
- 자바쪽 핵심은 Spring, JPA 두개인듯
- 유겐 휠러 (Juergen Hoeller)
- 강의 컨셉은 초창기 스프링의 핵심 30_000 라인 정도의 코드를 이해하는 것
- 스프링 부트(2014) 이거 나온것도 나름 전환점

## 스프링이란?
- https://spring.io
- 필수 : 프레임워크/부트 
- 선택 : 스프링 데이터, 세션, 시큐리티 , 배치, 클라우드 등등

- 핵심 : di 컨테이너, aop, 이벤트 기타 
- 나머지 : 웹,데이터(jdbc,orm,트랜잭션) 테스트 방법 등등은 부차적인 것

- 최근은 스프링 부트를 통해서 스프링을 편하게 사용

- 스프링 부트 
    - 스프링 앱 쉽게 생성
    - 톰캣 설치 필요 없음
    - 버전 관리 용이 (외부용)
    - 모니터링 (액추에이쳐)
    - 관례로 설정 (편리함-특히 로컬은 설정할게 별게 없음)
    
### 스프링이란?
- 생태계 (스프링 좋다~)
- 컨테이너 : di 컨테이너 (어플리케이션 컨텍스트)

- 뭐니?
 - 웹 어플리케이션? db 접근 기술?
 - 전자정부 프레임워크?
 - 웹 서버 띄워주고, 자동으로 띄워주고
 - 클라우드, msa ?
 
### 진짜 개념
- <B> 좋은 객체 지향 프로그래밍을 도와주는 프레임워크 </B>

### 객체 지향 특징
- 캡슐
- 추상
- 상속
- 다형성 - 갈아끼우기 쉽게 하는거 (역할과 / 책임으로 구분)

### 실생활 비유 예제는 그냥 가볍게 보고 넘어가며 좋을듯
(운전자 - 자동차 관계)
(로미오 - 줄리엣 공연)
(키보드 - 마우스)
(정렬 알고리즘 변경)
(할인 정책)

### 핵심은 클라(중요)
- 클라는 인터페이스만 알면 됨
- 구현체 몰라도 됨
- 구현체가 내부가 바뀌어도 클라는 상관없이 동작함
- 구현 대상 자체를 변경해도 문제 없음 (할인정책 a 에서 b를 바라봐도 중요)

### 자바세계에서
역할 = 인터페이스
구현 = 인터페이스 구현한 것

객체는 서로 협력 관계라는 것을 인지하는 것이 시작점
클라 : 요청
서버 : 응답

오버라이딩
- (Interface) MemberRepository.save()  / MemberMemoryDBRepository.save() / MemberJPARepository.save()

### 다형성의 본질
- 실행 시점에 실행할 객체 인스턴스를 고를 수 있다.
- 클라는 내비두고, 서버만 변경하는게 다형성 핵심

아무튼 인터페이스 잘 설계하는 것 중요


## 좋은 oop 설계 원칙

- Single Responsibility Principle 단일 책임 -- 한 클래스는 하나의 책임만 가져야 한다(애매한 개념 -> 코드 수정시 변경 사항 적으면 잘 지켰다고 생각하자.)
- Open Closed Principle 개방-폐쇄 원칙 -- 인터페이스를 구현한 클래스를 또 찍어내는것 (ex> 위에 MemberJPARepository 구현한 것) 

```
    MemberRepository m = new MemoryMemberRepository();//기존 
    MemberRepository m = new JPAMemberRepository();//변경
```

  - 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
  - 다형성을 맞지만 ocp는 아니죠.
  - 이를 해결하려면 별도로 객체를 생성하고 조립해주는 설정자가 필요하다는 걸 알 수 있죠. <= 이걸 스프링 컨테이너가 해줍니다!

- LSP 리스코프 치환 법칙
 - 인터페이스 규약 지켜라 (솔직히 너무 당연한 말이라 이걸 왜 법칙으로 지정했는지 모르겟)
 
- ISP 원칙
 - 범용 인터페이스보단 각 클라이언트에 대한 인터페이스를 구현하는게 낫다.
 - 잘게 잘게 쪼개
 - 자동차 인터페이스 -> 운전 인터페이스,정비 인터페이스
 - 사용자 인터페이스 -> 운전 클라, 정비 클라
 
- DIP 원칙 (중요)
 - 클라 코드가 구현체 말고 인터페이스 바라바롸.

```
    MemberRepository m = new JPAMemberRepository();//추상,구체 둘다 의존 하고 있다,(위반)
```

<b> 정리하면 다형성(폴리모피즘) 만으로는 ocp, dip 지킬 수 없음. -> 뭔가 더 필요하다. </b>

## 객체 지향 설계와 스프링

- di : 의존성 주입
- di 컨테이너

요 2가지 갖고 ocp,dip 를 지원해줌

정리하면 역할,구현을 구분해서 구현


# 스프링 핵심 원리 이해1 - 예제 만들기

자바 8 버전으로 진행함

- 스프링 이니샬라이져 -> 쉽게 스프링 프로젝트 만들 수 있음
- 스냅샷이랑 m 버전은 안정 버전이 아님 
- dev-001 브랜치 참고

## 요구사항
- 회원
 - 가입 & 조회
 - 일반 & vip 등급
 - db 는 미정
- 주문 & 할인
 - 회원은 상품 주문 가능
 - 등급에 따라 할인 정책
 - 고정 할인 갈 수도 있고 아닐 수도 있고 (미정)


## 회원 도메인 설계


```
public class Member {

  private Long id;
  private String name;
  private Grade grade;

```

```
public enum Grade {
  BASIC,
  VIP
}
```


```
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();// dbMemberRepository() <- ㅇㅣ런 식으로 넣어주는건 ocp 위반

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
```

```
public class MemoryMemberRepository implements MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();

  @Override
  public void save(Member member) {
    store.put(member.getId(),member);
  }

  @Override
  public Member findById(Long memberId) {
    return store.get(memberId);
  }
}

```

검증

```
class MemberServiceTest {

  MemberService memberService = new MemberServiceImpl();

  @Test
  void join() {
    //given
    Member member = new Member(1L, "memberA", Grade.VIP);

    //when
    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    //then
    assertThat(member).isEqualTo(findMember);
  }

}
```

문제점

```
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();

```


## 주문,할인 도메인 설계,개발,테스트

대부분은 생략할거고 

```

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();

  //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
  ...

```


# 객체 지향 원리 적용

```
public interface DiscountPolicy {
  int discount(Member member, int price);
}
```

```
public class RateDiscountPolicy implements DiscountPolicy {

  private int discontPercent = 10;

  @Override
  public int discount(Member member, int price) {

  ...
  
 public class FixDiscountPolicy implements DiscountPolicy {
 
 ...
 
 
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

```

## 새로운 할인 정책 적용


### 문제점  (완전 중요)
- 인터페이스에만 의존해야 하는데, 추상, 구체 클래스 둘다 의존한다. dip 위반 하는 것
- 할인 정책 바꾸는 순간 서비스 코드 바꿔야 하는데 이건 ocp 위반 하는것

### 해결 방법
 - 인터페이스에만 의존하도록 하면 된다!
 
 ```
  private final DiscountPolicy discountPolicy;
 ```
 
 이렇게만 하면 npe 발생하지
 이 문제를 해결하려면 누군가 클라이언트인 OrderServiceImpl 에 discountPolicy 의 구현 객체를 대신 생성하고 주입시켜줘야 함.
 
 
 ## 관심사 분리
 
 구현객체를 생성하고 연결하는 책임을 가진 별도의 클래스를 생성하자 (공연 기획자)
 
 ```
 public class AppConfig {
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
```

```
public class MemberServiceImpl implements MemberService {

  private MemberRepository memberRepository;
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

```

```
public static void main(String[] args) {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(1L);
    System.out.println("new member = " + member.getName());
    System.out.println("find Member = " + findMember.getName());
  }
```

### AppConfig 는 구현 객체를 생성한다.

### AppConfig 는 객체 인스턴스의 주소값(레퍼런스)을 생성자를 통해 주입(연결) 해준다.

ex> OrderServiceImpl(memberRepository(), discountPolicy());

객체의 생성과 연결은 AppConfig 가 담당한다. -> dip 완성!

할인정책 바꾸고 싶으면 AppConfig 만 바꾸면 된다!! (구성영역만 변경) / 사용영역은 안 건드려도 어플리케이션 잘 돌아간다.


## IOC, DI, 컨테이너

- 제어의 역전 (AppConfig)
- 프레임워크 vs 라이브러리 (Junit / Gson)

- 의존관계 주입 (정적 클래스/동적 클래스)
  - 두개 구분해서 생각해야함.
  - 정적인 것 <- 클래스 다이어그램
  - 동적인 것 <- 객체 다이어그램 (애플리케이션 실행 시점(런타임)에 결정) (애플리케이션 코드를 안건드린다 / 물론 구성 영역은 변경 되겠지만)
  
  
 - IOC 컨테이너 또는 DI 컨테이너 
 - 객체 생성하고, 주입해주는 녀석 AppConfig 생각하면 됨.
 
 
# 스프링으로 전환하기
지금까지 순수 자바 코드로 di 를 짬. (dev-002 브랜치)
이제 스프링으로 바꿔보자

dev-003 브랜치 참고

```
/**
 * 설정정보
 * @Bean을 등록하면 스프링
 */
@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

}
```
이렇게 하면 각 빈들이 스프링 컨테이너에 등록됨

```
public class MemberApp {

  public static void main(String[] args) {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);//메소드명,반환타입

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(1L);
    System.out.println("new member = " + member.getName());
    System.out.println("find Member = " + findMember.getName());
  }
}

```
- ApplicationContext 를 스프링 컨테이너라 함.
- 기존에는 AppConfig 에서 직접 객체를 생성하고 di 했지만, 이제는 컨테이너 사용
- 스프링 컨테이너는 @Configuration 이 붙은 앱컨피그를 구성 정보로 사용한다. 빈이라 적힌 메소드를 모두 호출해서 반환된 객체를 컨테이너에 등록합니다. 요리 등록된 객체를 스프링 빈이라 함.
- 스프링 빈은 메소드의 이름을 스프링 빈의 이름으로 사용함.
- 이젠 스프링 컨테이너에 빈을 등록하고, 스프링 컨테이너에서 찾아 쓰는 방식으로 변경되었다고 볼 수 있음.
- 장점이 무엇일까? 

# 스프링 컨테이너와 빈

## 
```
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

```
- ApplicationContext 를 스프링 컨테이너라 한다.
- ApplicationContext 는 인터페이스이다.
- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다. 직전에 AppConfig 를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것 이다.
- 자바 설정 클래스를 기반으로 스프링 컨테이너( ApplicationContext )를 만들어보자.
```
new AnnotationConfigApplicationContext(AppConfig.class); 
```
- 이 클래스는 ApplicationContext 인터페이스의 구현체이다.

## 과정
AppConfig.class -> 스프링 컨테이저 (빈 저장소)에 저장됨
key   :  method name (카멜로 지어주는게 관례)
value :  참조값(주소값)

빈 이름을 직접 부여할 수 있지만, 쉽게 쉽게 가는게 좋다. 
빈 이름 중복 안되게 하는게 좋음(관례) [문제는 쉽게 쉽게 푸는게 짱이다] 예전에는 빈이름 중복되면 덮어버렸는데 요즘은 튕겨버린다.

1] 빈 등록
2] 빈 주입 (di) (주소 가르치는 것)


실제로는 자바 코드로 스프링 빈 을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.


## 컨테이너에 등록된 빈 조회 방법

```
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
```

```
@Test
  @DisplayName("애플리케이션 빈 출력")
  void findApplicationBeans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
    
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);//오브젝트로 나오는 이유 -> 리턴이 뭐가 될 지 모르니까!!!
        System.out.println("name -> " + bean);
      }
    }
  }
```

```
    ROLE_APPLICATION : 내가 직접 등록한 빈
    ROLE_INFRASTRUCTURE : 스프링에서 등록한 빈
```

## 빈 조회 방법

- 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
- ac.getBean(빈이름, 타입)
- ac.getBean(타입)
- 조회 대상 스프링 빈이 없으면 예외 발생

 구체적인 코드는 AppApplicationContextSameBeanFindTest.java 파일 참고

구체타입으로 조회하면 유연성은 떨어지는 거지, 빈 이름으로 조회하는게 아무래도 좀 더 유연함.

## 동일한 타입 2개 이상인 경우

ApplicationContextExtendsFindTest.java 파일 참고

## 상속 관계 조회
부모 타입으로 조회하면, 자식 타입도 함께 조회한다. (쫘악 끌려 나온다)

모든 코드들은 사실 extends Object 라 보면 된다. 단지 생략된것일뿐

ApplicationContextExtendsFindTest.java


## BeanFactory와 ApplicationContext

결론부터 애기하면 우리는 거의 ApplicationContext 만 사용한다.

빈팩토리 - 최상위 인터페이스
그 아래에는 어플리케이션 컨텍스트 (빈 팩토리 + 부가 기능)
그 아래에 어노테이션컨피그 어플리케이션 컨텍스트가 있음

- BeanFactory
 - 스프링 컨테이너의 최상위 인터페이스다.
 - 스프링 빈을 관리하고 조회하는 역할을 담당한다.
 - getBean() 을 제공한다.
 - 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.

- ApplicationContext
 - BeanFactory 기능을 모두 상속받아서 제공한다.
 - 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는데, 그러면 둘의 차이가 뭘까? 애플리케이션을 개발할 때는 빈은 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다.
 - 메시지소스를 활용한 국제화 기능 (ex> 예를 들어서 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력 환경변수
 - 로컬, 개발, 운영등을 구분해서 처리 애플리케이션 이벤트
 - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
 - 편리한 리소스 조회
 - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회


## 스프링 빈 설정 메타 정보 - BeanDefinition

- BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수 도 있다. 하지만 실무에서 BeanDefinition을 직접 정의하거나 사용할 일은 거의 없다. 어려우면 그냥 넘어가면 된다^^!
- BeanDefinition에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를 BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 된다.
- 가끔 스프링 코드나 스프링 관련 오픈 소스의 코드를 볼 때, BeanDefinition 이라는 것이 보일 때가 있다. 이때 이러한 메커니즘을 떠올리면 된다.


1] Bean Definition 으로 스프링 빈 설정 메타 정보를 추상화한다.
2] 만드는 방식 2가지 -> 직접 등록 / 팩토리 빈을 통해 등록 (일반적으로 컨피그를 쓰는게 팩토리 빈을 통해 등록하는 거라 보면 됨)


# 싱글톤 컨테이너

SingleTonTest.java

```
@Test
  @DisplayName("스프링 없는 순수 di 컨테이너")
  void 순수() {
    AppConfig appConfig = new AppConfig();
    MemberService m1 = appConfig.memberService();
    MemberService m2 = appConfig.memberService();

    System.out.println("m1:" + m1.toString());
    System.out.println("m2:" + m2.toString());

    assertThat(m1).isNotSameAs(m2);

  }
```

##  싱글톤 패턴
- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다. 
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
 - private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.


```
public class SingletonService {

  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance;
  }

  //누군가 new 로 만들지 못하게 막는 거.
  private SingletonService() {
  }

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }
}

  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  void 싱글톤_객체_테스트() {
    SingletonService sg1 = SingletonService.getInstance();
    SingletonService sg2 = SingletonService.getInstance();
    System.out.println(sg1.toString());
    System.out.println(sg2.toString());
    assertThat(sg1).isSameAs(sg2);
  }
```

스프링 컨테이너 -> 기본적으로 싱글톤


싱글톤 패턴을 적용하면 고객 요청 올 때마다 객체를 생성하는 것이 아니라, 이미 만들어 놓은 객체를 재활용한다.

싱글톤 패턴 문제점

- 코드 양 많음
- 클라가 구체 클래스에 의존 -> solid (ocp,dip 위반) 
- 테스트하기 어렵
- 내부 속성 변경/초기화 하기 어렵
- 결론적으로 유연성이 떨어짐
- 안티패턴

싱글톤 컨테이너는 위의 문제를 해결해줌.

## 싱글톤 컨테이너

스프링 컨테이너는 싱글톤 문제를 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
그동안 위에 AppConfig 에 빈 달아주고 했던게 싱글톤

### 싱글톤 컨테이너
- 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 이런 기능 덕분에 싱글톤 패턴의 모든 단점을 해결하면서 객치를 싱글톤으로 유지할 수 있다.
 - 지저분한 코드 불필요
 - solid 위반(x) , private 생성자로 부터 자유롭게 싱글톤 사용 가능.

```
@Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  void name() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService m1 = ac.getBean("memberService", MemberService.class);
    MemberService m2 = ac.getBean("memberService", MemberService.class);

    System.out.println("m1:" + m1.toString());
    System.out.println("m2:" + m2.toString());

    assertThat(m1).isSameAs(m2);
  }
  //m1:hello.core.member.MemberServiceImpl@6253c26
  //m2:hello.core.member.MemberServiceImpl@6253c26
```


- 싱글톤 컨테이너 적용 후
 - 각각의 클라이언트가 memberSerivce 를 요청해도 동일한 memberService 를 반환한다.
 - 컨테이너 덕분에 고객의 요청이 들어올 때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.

| 참고 : 스프링 빈 등록방식은 기본적으로 싱글톤 방식임, 물론 이런 방식만 지원하는 것은 아님(빈 스코프). 99%는 싱글톤 쓴다.


## 주의사항 (몇년에 한 번씩 고난을 겪는)

- stateful (x)
- stateless (o)

```
    여러 클라이언트가 하나의 객체를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
```

- 무상태(stateless)하게 설계하면 안된다.
 - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
 - 특정 클라가 값을 변경할 수 있는 필드가 있으면 안된다!
 - 가급적 읽기만 가능해야 한다.
 - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.

- 스프링 빈의 필드에 공유값을 설정하면 정말 큰 장애가 발생할 수 있다.

```
public class StatefulService {

  private int price;//10,000 -> 20,000

  public void Order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    this.price = price; //여기가 문제!
  }

  public int getPrice() {
    return price;
  }
}

  @Test
  @DisplayName("이렇게 되면 망하는거야~~~, 이 예제는 단순한 건데 멀티스레드 환경에선 더 복잡하겠지. (멀티스레드는 어디서 공부하는게 효율적일까) ")
  public void satefulServiceSingleton_망하는_케이스() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService s1 = ac.getBean(StatefulService.class);
    StatefulService s2 = ac.getBean(StatefulService.class);

    //thread a: useA 10_000 주문
    s1.Order("userA", 10000);
    
    //thread b: userB 20_000 주문
    s2.Order("userB", 20000);

    //ThreadA: 사용자a 주문 금액 조회 (a 가 주문하고 조회하려고 하는데, b의 주문 금액이 출력되는 꼴) / 기대값은 10_000 원, 
    int price = s1.getPrice();
    System.out.println("price : " + price);
    Assertions.assertThat(s1.getPrice()).isEqualTo(20000);
  }
  
  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }

    @Bean
    public StatefulServiceWIthoutField statefulServiceWIthoutField() {
      return new StatefulServiceWIthoutField();
    }

  }
```
- 최대한 단순하게 설명한 예제이다.
- ThreadA가 사용자 A 코드를 호출하고 ThreadB가 사용자 B 코드를 호출한다고 가정.
- StatefulService 의 price 필드는 공유되는 필드인데, 특정 클라가 값을 변경한다.
- 사용자 A 의 주문금액은 10_000 원이 되어야 하는데, 20_000 원이라는 결과가 나왔다.
- 실무에서 몇년에 한번 이런 류의 문제가 터진다고 한다.
- 공유 필드는 정말 정말 조심해야 한다. 스프링 빈은 항상 stateless 로 설계해야 한다.

- 솔루션 => 멤버 변수를 빼버리면 된다.
```
public class StatefulServiceWIthoutField {

  public int Order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    return price;
  }
  
  
  static class TestConfig {
   @Bean
    public StatefulServiceWIthoutField statefulServiceWIthoutField() {
      return new StatefulServiceWIthoutField();
    }
  }
  
  @Test
  @DisplayName("필드를 없앴소~")
  public void satefulServiceSingleton_보완한_케이스() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulServiceWIthoutField s1 = ac.getBean(StatefulServiceWIthoutField.class);
    StatefulServiceWIthoutField s2 = ac.getBean(StatefulServiceWIthoutField.class);

    int price1 = s1.Order("userA", 10000);
    int price2 = s2.Order("userB", 20000);

    Assertions.assertThat(price1).isEqualTo(10000);
    Assertions.assertThat(price2).isEqualTo(20000);
  }
```
ex> 나의 아이디인데 다른 사람 이름, 나의 결재 내역인데 다른 사람 결재 내역... => 복구하는데 몇 달 걸림.
위의 예제니까 잘 보이지만, 상속 관계에서 복잡한데선 잘 안보일수도.

```
공유필드는 항상 조심.
```

## @Configuration 과 싱글톤


```
@Configuration
public class AppConfig {

  //@Bean memberService -> new MemoryMemberRepository()
  //@Bean orderService -> new MemoryMemberRepository()
  //call AppConfig.memberService
  //call AppConfig.memberRepository
  //call AppConfig.memberRepository
  //call AppConfig.orderService
  //call AppConfig.memberRepository
  //


  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}

```


```
  //MemberServiceImpl,OrderServiceImpl 에 추가
  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
```


```
 @Test
  public void dummy() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository m1 = memberService.getMemberRepository();
    MemberRepository m2 = orderService.getMemberRepository();

    System.out.println("memberService -> memberRepository = " + m1.toString());
    System.out.println("memberService -> memberRepository = " + m2.toString());
    System.out.println("memberRepository = " + memberRepository);
    Assertions.assertThat(m1).isSameAs(m2).isSameAs(memberRepository);

  }
```
세개다 같음.

그리고 호출도 3번만 호출함.

예상(순서는 보장 못함)
call AppConfig.memberService 
call AppConfig.memberRepository
call AppConfig.memberRepository
call AppConfig.orderService 
call AppConfig.memberRepository

실제
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService

스프링이 어떻게든, 싱글톤을 보장해준다는 것을 알 수 있음.


## @Configuration 과 바이트 코드 조작의 마법

스프링이 자바 코드까진 어떻게 하진 못함.
그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
모든 비밀은 @Configuration 을 적용한 AppConfig 에 있다.

```
 @Test
  void congiutrationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());//이건 내가 만든 클래스가 아니다!!!!
  }
  
//[console] bean = class hello.core.config.AppConfig$$EnhancerBySpringCGLIB$$f8f0ae19
```
순수한 자바 클래스는 class hello.core.AppConfig 이런 식으로 찍혀야 함.

이것은 내가 만든 클래스가 아니라 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.


```
@Configuration 코드 AppConfig.java 에서 빼도 동작은 한다.
하지만 싱글톤은 깨진다. 상단의 테스트 돌려봐도 결과가 깨진다.
```
뭐 사실 @Autowired 를 사용하면 해결할 수 있긴 한데, 이건 나중.


정리
-  @Bean 만 드록해도 스프링 빈으로 등록되지만, 싱글톤을 보장하진 않는다.
 -  memberRepository() 처럼, 의존주입이 필요해서 메소드를 직접 호출할 때 싱글톤을 보장하지 않는다.
-  <b> 크게 고민할필요없다. 스프링 설정 정보는 항상 @Configuration 을 사용하자. </b>

# 컴포넌트 정리

## 컴포넌트 스캔과 의존관계 자동 주입 시작하기

- 어떻게 이걸 다 일일이 등록할래?? (휴먼에러 발생한다)
- 스프링은 설정 정보가 없어도 자동으로 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
- 또 의존관계도 자동으로 주입하는 @Autowired 기능도 제공한다.

- 컴포넌트 스캔은 이름 그대로 @Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.

| 참고 : @Configuration 이 컴포넌트 스캔의 대상이 된 이유도 @Configuration 소스코드를 열어보면 @Component 어노테이션이 붙어있기 때문이다.


```

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//예제를 돌리기 위해서 넣어둠. AppConfig Configuration 들어가면 @component 달려있음. 고로 이녀석도 검색 대상이 되면서 충돌나요
//컨피규레이션 어노테이션 달린건 컴포넌트 스캔 대상에서 제외시킬거야
//실무에서는 이러진 않겠죠. 이렇게 한 것은 예제코드를 살리기 위해서!!!
public class AutoAppConfig {
...
@Component
public class MemoryMemberRepository implements MemberRepository{
...

```

그런데 의존관계 주입은?

```
@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    
    @Autowired//<- 요거 //ac.getBean(MemberRepository.class) 와 비슷
    public MemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
}
```


```
public class AutoAppConfigTest {
  @Test
  void basicScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
```
로그 보면 컴포넌트 스캔 잘 되는 것도 볼 수 있다.


@ComponentScan은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
이때 스프링 빈의 기본 이름은 클래스 명을 사용하되 맨 앞글자만 소문자를 사용한다.
- 빈 이름 전략 : MemberServiceImpl 클래스 -> memberServiceImpl
- 빈 이름 직접 지정 : 만약 스프링 빈 이름을 직접 지정하고 싶으면 @Compoent("memberXXX") 뭐 이런식으로 지정하면 된다.
- 생성자에 Autowired 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
- 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
 - getBean(MemberRepository.class) 와 동일하다고 이해하면 된다.
- 생성자에 파라미터가 많아도 자동으로 알아서 촥촥촥 주입.

## 탐색 위치와 기본 스캔 대상
탐색 위치 지정할 수 있음 (basePackages = "") <- 이거 없으면 라이브러리도 탐색을 하기 때문에 유용하게 쓰이는 옵션이다~
탐색 위치 여러개 지정할 수도 있음.
basePackageclasess=""

default => @ComponentScan 붙인 패키지부터 하위 싹 다 찾음. 
- 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

### 권장 방법
개인적으로 즐겨 사용하는 방법 : 패키지 위치 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.


예를 들어 아래와 같은 프로젝트 구조라 치자.

- com.hello
- com.hello.service
- com.hello.repository

- com.hello -> 프로젝트 시작 루트, 여기에 AppConfig 같은 메인 설정을 두고, @ComponentScan 애노테이션을 붙이고, basePackages 는 지정 생략한다..

이렇게 하면 com.hello 를 포함한 하위는 스캔 대상이 된다.
스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다. (그리고 이 설정 안에 바로 @ComponentScan이 들어있다!)

스캔 기본 대상
- @Controller
- @Service
- @Repository
- @Configuration
- @Component


 참고: 사실 애노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.
컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 스프링은 부가 기능을 수행한다. 
- @Controller : 스프링 MVC 컨트롤러로 인식
- @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다. 
- @Configuration : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리함.
- @Service : 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있구나 라고 비즈니스 계층을 인식하면 된다.
> 참고: useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다. 그 냥 이런 옵션이 있구나 정도 알고 넘어가자.


## 필터
- includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
- excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.




