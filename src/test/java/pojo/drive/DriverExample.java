package pojo.drive;

public class DriverExample {

    public static void main(String[] args) {

        Driver driver = new Driver();

        Bus bus = new Bus();
        Taxi taxi = new Taxi();

        System.out.println("driver = " + driver);
        System.out.println("bus = " + bus);
        System.out.println("taxi = " + taxi);

        driver.drive(bus);//Vehicle vehicle = bus;
        driver.drive(taxi);//Vehicle vehicle = taxi;

    }
}
