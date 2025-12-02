public class TV {

    private String id;
    private boolean on = false;
    private int channel = 1;
    private int volume = 10;

    public TV(String id) {
        this.id = id;
    }

    public void on() {
        on = true;
        System.out.println(id + " TV ON");
    }

    public void off() {
        on = false;
        System.out.println(id + " TV OFF");
    }

    public void setInputChannel(int channel) {
        this.channel = channel;
        System.out.println(id + " TV channel set to " + channel);
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(id + " TV volume set to " + volume);
    }

    public boolean isOn() {
        return on;
    }

    public int getChannel() {
        return channel;
    }

    public int getVolume() {
        return volume;
    }

    public String getId() {
        return id;
    }
}
