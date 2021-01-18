package pojo.nestedaccess;

//로컬 클래스에서 변수 사용법
public class Outter {

    public void method2(int arg) {
        int localVariable = 1;
//        arg = 100;
//        localVariable = 100;
        class Inner {
            public void method() {
                int result = arg + localVariable;
            }
        }
    }


}
