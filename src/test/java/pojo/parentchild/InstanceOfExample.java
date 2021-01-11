package pojo.parentchild;

public class InstanceOfExample {

    public static void method1(Parent parent){
        //좌향(객체) instanceof 우향(타입)
        if(parent instanceof Child){
            Child child = (Child) parent;
            System.out.println("method1 child 로 변경 성공");
            System.out.println("ref = " + child);
            System.out.println("class = " + child.getClass());
        }else{
            System.out.println("method2 child 로 변경 안됨");
            System.out.println("ref = " + parent);
        }
    }

    public static void method2(Parent parent){
        Child child = (Child) parent;
        System.out.println("InstanceOfExample.method2");
    }

    public static void main(String[] args) {

        Parent parentA = new Child();
        method1(parentA);
        method2(parentA);

        Parent parentB = new Parent();
        method1(parentB);
        method2(parentB);
    }


}
