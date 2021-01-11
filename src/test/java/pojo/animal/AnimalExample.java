package pojo.animal;

public class AnimalExample {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Animal animal = cat;
        System.out.println("cat = " + cat);
        System.out.println("ani = " + animal);
        System.out.println("animal == cat " + (animal == cat));
    }
}
