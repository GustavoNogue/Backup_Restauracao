public class Sound {

    private String id;
    private String mode = "OFF";
    private int volume = 5;

    public Sound(String id) {
        this.id = id;
    }

    public void on() {
        System.out.println(id + " Sound ON");
    }

    public void off() {
        System.out.println(id + " Sound OFF");
    }

    public void setCd() {
        mode = "CD";
        System.out.println(id + " Sound set to CD mode");
    }

    public void setDvd() {
        mode = "DVD";
        System.out.println(id + " Sound set to DVD mode");
    }

    public void setRadio() {
        mode = "RADIO";
        System.out.println(id + " Sound set to RADIO mode");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(id + " Sound volume set to " + volume);
    }

    public String getMode() {
        return mode;
    }

    public int getVolume() {
        return volume;
    }

    public String getId() {
        return id;
    }
}
