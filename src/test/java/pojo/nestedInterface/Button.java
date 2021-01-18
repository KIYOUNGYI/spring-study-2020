package pojo.nestedInterface;

public class Button {

    OnClickListener listener;//인터페이스 타입 필드

    void setOnClickListener(OnClickListener listener) {//매개변수의 다형성
        this.listener = listener;
    }

    void touch() {// 구현 객체의 온클릭 메소드 호출
        listener.onClick();
    }


    interface OnClickListener {//중첩 인터페이스

        void onClick();
    }
}
