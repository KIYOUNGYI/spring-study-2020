package pojo.utilsample.equals;

public class MemberExample {

    public static void main(String[] args) {

        Member obj1 = new Member("blue");
        Member obj2 = new Member("blue");
        Member obj3 = new Member("red");

        if (obj1.equals(obj2)) {
            System.out.println("obj1 과 obj2 동일");
        } else {
            System.out.println("obj1 과 obj2 != 동일");
        }

        if(obj1.equals(obj3)){
            System.out.println("obj1 과 obj3은 동등합니다.");
        } else {
            System.out.println("obj1 과 obj3은 동등 != ");
        }

    }
}
