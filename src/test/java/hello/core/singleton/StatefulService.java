package hello.core.singleton;

public class StatefulService {

  private int price;//10,000 -> 20,000

  public void Order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    this.price = price; //여기가 문제!
  }

  public int getPrice() {
    return price;
  }
}
