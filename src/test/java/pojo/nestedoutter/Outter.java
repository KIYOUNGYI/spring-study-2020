package pojo.nestedoutter;

public class Outter {
    String filed = "Outter-field";

    void method1() {
        System.out.println("Outter.method1");
    }

    class Nested {
        String field = "Nested-field";

        void method() {
            System.out.println("Nested.method");
        }

        void print() {
            //중첩 객체 참조
            System.out.println(this.field);
            this.method();

            //바깥 객체 참조
            System.out.println(Outter.this.filed);
            Outter.this.method1();

        }
    }

    public static void main(String[] args) {
        Outter outter = new Outter();
        Outter.Nested nested = outter.new Nested();
        nested.print();
    }
}
