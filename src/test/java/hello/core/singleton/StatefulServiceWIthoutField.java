package hello.core.singleton;

public class StatefulServiceWIthoutField {


  public int Order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    return price;
  }


}
