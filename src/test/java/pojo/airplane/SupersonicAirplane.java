package pojo.airplane;

public class SupersonicAirplane extends Airplane {

    public static final int NORMAL = 1;
    public static final int SUPERSONIC = 2;

    public int flyMode = NORMAL;

    @Override
    public void fly() {

        if (flyMode == SUPERSONIC) {
            System.out.println("SupersonicAirplane.fly");
        } else {
            System.out.println("Airplane 객체의 fly 메소드 호출");
            super.fly();
        }
    }
}
