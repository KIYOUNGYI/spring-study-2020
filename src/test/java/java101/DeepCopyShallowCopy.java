package java101;

// clone()메서드로 깊은 복사를 진행한 physicalInformationDeepCopy 는 얕은 복사를 한 객체와는 달리 참조값도 다르고 그에 따라 값도 변경되지 않았음을 알 수 있다.

public class DeepCopyShallowCopy {

  public static void main(String[] args) {
    PhysicalInformation physicalInformation = new PhysicalInformation();
    physicalInformation.weight = 70;
    physicalInformation.height = 180;

    PhysicalInformation physicalInformationShalldowCopy = physicalInformation;

    PhysicalInformation physicalInformationDeepCopy = null;
    try {
      physicalInformationDeepCopy = (PhysicalInformation) physicalInformation.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    // 값 변경
    physicalInformation.weight = 180;
    physicalInformation.height = 70;

    System.out.println(physicalInformation.toString() + " height:" + physicalInformation.height + ", weight:" + physicalInformation.weight);

    System.out.println(physicalInformationShalldowCopy.toString() + " height:" + physicalInformationShalldowCopy.height + ", weight:" + physicalInformationShalldowCopy.weight);

    System.out.println(physicalInformationDeepCopy.toString() + " height:" + physicalInformationDeepCopy.height + ", weight:" + physicalInformationDeepCopy.weight);


  }

  static class PhysicalInformation implements Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {
      return super.clone();
    }

    int height;
    int weight;
  }

  static class Family {

    String name;
    int age;
    boolean isOfficeWorkers; // 직장인 여부

  }

  static class Student {

    int age;
    Family family;
  }
}
