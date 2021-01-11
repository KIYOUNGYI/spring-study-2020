package pojo.human;

public class Student extends People {

    public int studentNo;

    public Student(String name, String ssn, int studentNo) {
        super(name, ssn);
        this.studentNo = studentNo;
    }

    @Override
    public void method2() {
        System.out.println("Student.method2");
    }

    public void studentMethod(){
        System.out.println("Student.studentMethod");
        super.method3();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNo=" + studentNo +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
