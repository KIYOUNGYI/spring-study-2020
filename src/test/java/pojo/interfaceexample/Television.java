package pojo.interfaceexample;

import static pojo.interfaceexample.RemoteControl.*;

public class Television implements RemoteControl {

    private int volume;

    public void turnOn() {
        System.out.println("Television.turnOn");
    }


    public void turnOff() {
        System.out.println("Television.turnOff");
    }

    public void setVolume(int volume) {
        if (volume > MAX_VOLUME) {
            this.volume = MAX_VOLUME;
        } else if (volume < MAX_VOLUME) {
            this.volume = MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println("현재 볼륨 = " + volume);
    }
}
