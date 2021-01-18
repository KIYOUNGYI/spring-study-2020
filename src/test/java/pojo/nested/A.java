package pojo.nested;

public class A {

    A() {
        System.out.println("A.A");
    }

    /* instance member class */
    class B {

        //constructor
        B() {
            System.out.println("B.B");
        }

        int field1;

        //static int field2;//not working
        void method1() {
            System.out.println("B.method1");
        }
//      not working
//        static void method2(){}
    }

    static class C {

        public C() {
            System.out.println("C.C");
        }

        int field1;
        static int field2;

        void method1() {
            System.out.println("C.method1");
        }

        static void method2() {
            System.out.println("C.method2");
        }
    }

    void method1() {
        System.out.println("A.method1");
        /**로컬 클래스**/
        /**
         * 로컬 클래슨는 메소드가 실행될 때 메소드 내에서 객체를 생성하고 사용해야 한다.
         * 주로 비동기 처리를 위해 스레드 객체를 만들 때 사용함.
         * **/
        class D {

            D() {
                System.out.println("D.D");
            }

            int field1;

            void method1() {
            }
            //static fields, methods are not working.
        }
        D d = new D();
        d.field1 = 3;
        d.method1();
    }

    public static void main(String[] args) {
        A a = new A();

        //인스턴스 멤버 클래스 객체 생성
        A.B b = a.new B();
        b.field1 = 3;
        b.method1();

        //정적 멤버 클래스 객체 생성
        A.C c = new A.C();
        c.field1 = 3;
        c.method1();
        C.field2 = 3;
        C.method2();

        //로컬 클래스 객체 생성을 위한 메소드 호출
        a.method1();
    }


}
