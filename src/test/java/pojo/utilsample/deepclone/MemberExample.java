package pojo.utilsample.deepclone;

import java.util.Arrays;

public class MemberExample {

    public static void main(String[] args) {
        //원본 객체 생성
        Member original = new Member("홍", 25, new int[]{90, 90}, new Car("쏘나타"));

        //복제 객체를 얻은 후에 참조 객체의 값을 변경
        Member cloned = original.getMember();
        System.out.println("=>" + cloned.toString());
        cloned.scores[0] = 100;
        cloned.car.model = "그랜저";
//
        System.out.println("복제 객체 필드값");
        System.out.println("cloned.name:" + cloned.name);
        System.out.println("cloned.age:" + cloned.age);
        System.out.println("cloned.arr:" + cloned.scores[0]);
        System.out.println("cloned.car:" + cloned.car);
    }
}
