package pojo.interfaceexample;

public class SmartTelevision implements Searchable, RemoteControl {

    private int volume;

    public void turnOn() {
        System.out.println("SmartTelevision.turnOn");
    }

    public void turnOff() {
        System.out.println("SmartTelevision.turnOff");
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

    public void search(String url) {
        System.out.println("url = " + url);
    }
}
