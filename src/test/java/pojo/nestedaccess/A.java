package pojo.nestedaccess;

public class A {

    int field1;
    static int field2;

    void method1() {
        System.out.println("A.method1");
    }

    static void method2() {
        System.out.println("A.method2");
    }

    class B {

        void method3() {
            System.out.println("B.method3");
            field1 = 10;
            method1();

            field2 = 10;
            method2();
        }
    }

    static class C {

        void method4() {
            System.out.println("C.method4");
            field2 = 10;

            method2();
        }
    }
}

