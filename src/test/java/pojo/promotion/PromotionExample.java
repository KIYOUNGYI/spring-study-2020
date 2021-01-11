package pojo.promotion;

class A{}
class B extends A{}
class D extends B{}

class C extends A{}
class E extends C{}


public class PromotionExample {

    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        D d = new D();
        E e = new E();

        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("e = " + e);

        A a1 = b;
        A a2 = c;
        A a3 = d;
        A a4 = e;

        System.out.println(" a1 = " + a1);
        System.out.println(" a2 = " + a2);
        System.out.println(" a3 = " + a3);
        System.out.println(" a4 = " + a4);


    }
}
