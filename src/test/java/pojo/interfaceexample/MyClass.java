package pojo.interfaceexample;

public class MyClass {

    RemoteControl rc = new Television();

    MyClass(RemoteControl rc) {
        this.rc = rc;
    }

    void methodA() {
        RemoteControl rc = new Audio();
    }

    void methodB(RemoteControl rc) {
        this.rc = rc;
    }

    public static void main(String[] args) {
        MyClass mc = new MyClass(new Television());

        mc.methodB(new Audio());
    }
}
