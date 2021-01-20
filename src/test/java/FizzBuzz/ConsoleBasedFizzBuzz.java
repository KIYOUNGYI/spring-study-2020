package FizzBuzz;

public class ConsoleBasedFizzBuzz implements FizBuzz {


  @Override
  public void print(int from, int to) {
    for (int i = from; i <= to; i++) {
      if (i % 15 == 0) {
        System.out.println("FizzBuzz");
        continue;
      } else if (i % 3 == 0) {
        System.out.println("Fizz");
        continue;
      } else if (i % 5 == 0) {
        System.out.println("Buzz");
        continue;
      } else {
        System.out.println(i);
      }
    }
  }
}
