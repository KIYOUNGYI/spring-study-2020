package pojo.choosangoverriding;

public abstract class Animal {

    public String kind;

    public void breathe() {
        System.out.println("Animal.breathe");
    }

    public abstract void sound();
}
