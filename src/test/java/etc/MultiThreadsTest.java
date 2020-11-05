package etc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 위의 콘솔 결과를 보듯이 HashMap에 대한 부분은 동기화가 이루어지지 않아 엔트리 사이즈가 비정상적으로 나오는 것을 알 수 있습니다. 하지만 HashMap을 쓰더라도 synchronized 블록을 선언해 주면 정상으로 동작을 합니다. 따라서 동기화 이슈가 있다면 일반적인 HashMap을 쓰지 말거나 쓰더라도 동기화를 보장하는 HashMap 콜렉션
 * 또는 synchronized 키워드를 이용해 동기화 처리를 반드시 해주는 것이 좋아보입니다.
 *
 *  다만 내부적으로 put 연산에 대해 동기화 처리가 되지 않아 size 변수값이 비정상적으로 증가해서 저렇게 나오는 것이지요.
 *  그리고 put 연산에 대해 lock이 걸려 있지 않기 때문에 put에 대한 value 값이 호출 순서에 맞게 설정된다는 보장은 못하겠죠.
 */
public class MultiThreadsTest {

  private static final int MAX_THREADS = 10;

  private static Hashtable<String, Integer> ht = new Hashtable<>();
  private static HashMap<String, Integer> hm = new HashMap<>();
  private static HashMap<String, Integer> hmSyn = new HashMap<>();
  private static Map<String, Integer> hmSyn2 = Collections.synchronizedMap(new HashMap<String, Integer>());
  private static ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {

    ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS);

    setDataAndExecute(es);

    try {
      es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    printResult();
  }

  private static void setDataAndExecute(ExecutorService es) {
    for (int j = 0; j < MAX_THREADS; j++) {
      es.execute(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 1000; i++) {

            String key = String.valueOf(i);
            ht.put(key, i);
            hm.put(key, i);
            chm.put(key, i);
            hmSyn2.put(key, i);
//
            synchronized (hmSyn) {
              hmSyn.put(key, i);
            }
          }
        }
      });
    }
    es.shutdown();
  }

  private static void printResult() {
//    System.out.println("Hashtable size is " + ht.size());
    System.out.println("HashMap size is " + hm.size());
//    System.out.println("ConcurrentHashMap size is " + chm.size());
//    System.out.println("HashMap(synchronized) size is " + hmSyn.size());
//    System.out.println("synchronizedMap size is " + hmSyn2.size());

    for (String s : hm.keySet()) {
      System.out.println("[" + s + "] " + hm.get(s));
    }

  }


}