package pojo.human;

public class People {
    public String name;
    public String ssn;

    public People(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public void method1(){
        System.out.println("People.method1");
    }

    public void method2(){
        System.out.println("People.method2");
    }

    public void method3(){
        System.out.println("People.method3");
    }
}
