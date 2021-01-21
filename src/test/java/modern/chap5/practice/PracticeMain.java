package modern.chap5.practice;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PracticeMain {

  public static void main(String[] args) {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );
    problem001(transactions);
    problem002(transactions);
    problem003(transactions);
    problem004(transactions);
    problem005(transactions);
    problem006(transactions);
    problem007(transactions);
    problem008(transactions);
  }

  private static void problem008(List<Transaction> transactions) {
    Optional<Integer> reduce = transactions.stream().map(transaction -> transaction.getValue())
        .reduce(Integer::min);

    Optional<Transaction> reduce1 = transactions.stream()
        .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

    System.out.println("reduce = " + reduce);
    System.out.println("reduce1 = " + reduce1);

  }

  /**
   * [7] 전체 트랜잭션 중 최대값 reduce
   *
   * @param transactions
   */
  private static void problem007(List<Transaction> transactions) {
    Optional<Integer> reduce = transactions.stream().map(transaction -> transaction.getValue())
        .reduce(Integer::max);
    System.out.println("reduce = " + reduce);
  }


  /**
   * [6] 케임브리지에 거주하는 모든 사람의 트랜잭션값
   *
   * @param transactions
   */
  private static void problem006(List<Transaction> transactions) {

    List<Transaction> cambridge = transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).collect(toList());
    System.out.println("cambridge = " + cambridge);
  }


  /**
   * [5] 밀라노에 거래자가 있는가? anyMatch
   *
   * @param transactions
   */
  private static void problem005(List<Transaction> transactions) {
    boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    System.out.println("milan = " + milan);
  }

  /**
   * [4] 모든 거래자의 이름 알파벳 순으로 정렬해서 반환
   * <p>.sorted() 여도 되는구나</p>
   *
   * @param transactions
   */
  private static void problem004(List<Transaction> transactions) {
    List<String> collect = transactions.stream().map(Transaction::getTrader)
        .map(trader -> trader.getName())
        .sorted()
        .distinct()
        .collect(toList());

    List<String> collect2 = transactions.stream().map(transaction -> transaction.getTrader().getName())
        .sorted()
        .distinct()
        .collect(toList());

    System.out.println("collect = " + collect);
    System.out.println("collect2 = " + collect2);
  }


  /**
   * [3] 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
   *
   * @param transactions
   */
  private static void problem003(List<Transaction> transactions) {

//    List<Trader> collect = transactions.stream().map(Transaction::getTrader).collect(toList());

    List<Trader> collect = transactions.stream().map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .sorted(comparing(Trader::getName))
        .collect(toList());

//    transactions.stream().map(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
//        .sorted(comparing(Trader::getCity)).collect(toList());

    System.out.println("collect = " + collect);
  }


  /**
   * [2] 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
   *
   * @param transactions
   */
  private static void problem002(List<Transaction> transactions) {
    List<String> collect = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList());
    System.out.println("collect = " + collect);
  }

  /**
   * [1] 2011 년에 발생한 모든 트랜잭션을 찾아 오름차순으로 정렬
   *
   * @param transactions
   */
  private static void problem001(List<Transaction> transactions) {

    List<Transaction> collect = transactions
        .stream()
        .filter(transaction -> transaction.getYear() == 2011)
        .sorted(comparing(Transaction::getValue))
        .collect(toList());

    System.out.println("collect = " + collect);
  }


}
