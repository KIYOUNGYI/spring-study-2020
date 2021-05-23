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



