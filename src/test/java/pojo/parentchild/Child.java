package pojo.parentchild;

public class Child extends Parent {

    String field2;

    @Override
    public void method2() {
        System.out.println("Child.method2");
    }

    public void method3() {
        System.out.println("Child.method3");
    }
}
