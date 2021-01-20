package modern.chap5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamCamp {

  public static void main(String[] args) {
//    usingFlatMap();
//    reducingSum();
    reducingMax();
  }

  static void ordinaryApproach() {
    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");

    List<Integer> wordLengths = words.stream().map(String::length).collect(toList());

    System.out.println("wordsLengths:" + wordLengths);

  }

  static void flatWithProblem() {
    List<String> words = Arrays.asList("Hello", "World");
    //String[] 으로 맏는게 문제
    List<String[]> collect = words.stream()
        .map(word -> word.split(""))
        .distinct()
        .collect(toList());
  }


  //문자열 => stream
  static void concept() {
    String[] arrayOfWords = {"Goodbye", "World"};
    Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
    System.out.println("streamOfwords = " + streamOfwords);
  }

  static void usingFlatMap() {
    List<String> words = Arrays.asList("Hello", "World");
    List<String> collect = words.stream().map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct().collect(toList());
    System.out.println("collect = " + collect);
  }

  static void reducingSum() {
    List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
    int sum = numbers.stream().reduce(0, (a, b) -> (a + b));
    System.out.println("sum = " + sum);
  }

  static void reducingMax() {
    List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
    Optional<Integer> max = numbers.stream().reduce(Integer::max);
    Optional<Integer> min = numbers.stream().reduce(Integer::min);
    System.out.println("min = " + min);
    System.out.println("max = " + max);
  }


}
