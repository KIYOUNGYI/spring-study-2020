package pojo.parentchild;

public class ChildExample {

    public static void main(String[] args) {

//        hehe();
        System.out.println("===================");
        keke();

    }

    private static void keke(){
        Parent parent = new Child();//자동 타입 변환
        parent.method1();
        parent.method2();
//        parent.field2 = "yyy";
//        parent.method3();

        System.out.println("parent = " + parent);

        Child child = (Child) parent;//강제 타입 변환
        System.out.println("child = " + child);
        child.field2 = "hh";
        child.method3();

    }


    private static void hehe() {

        Child child = new Child();

        Parent parent = child;

        System.out.println("child = " + child);
        System.out.println("parent = " + parent);

        parent.method1();

        parent.method2();//재정의된 메소드가 호출됨.
    }
}
