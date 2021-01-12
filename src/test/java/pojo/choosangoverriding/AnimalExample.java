package pojo.choosangoverriding;

public class AnimalExample {

    public static void main(String[] args) {

        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.sound();
        cat.sound();
        System.out.println("========");

        Animal animal = null;
        animal = new Dog();
        System.out.println("animal = " + animal);
        animal.sound();
        animal = new Cat();
        System.out.println("animal = " + animal);
        animal.sound();
        System.out.println("========");

        animalSound(new Dog());
        animalSound(new Cat());
    }

    public static void animalSound(Animal animal) {
        System.out.println("animal = " + animal);
        animal.sound();
    }
}
