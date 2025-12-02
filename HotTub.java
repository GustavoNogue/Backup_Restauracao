public class HotTub {

    private String id;
    private int temperature = 25;

    public HotTub(String id) {
        this.id = id;
    }

    public void circulate() {
        System.out.println(id + " HotTub circulating water");
    }

    public void jetsOn() {
        System.out.println(id + " HotTub jets ON");
    }

    public void jetsOff() {
        System.out.println(id + " HotTub jets OFF");
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println(id + " HotTub temperature set to " + temperature);
    }

    public int getTemperature() {
        return temperature;
    }

    public String getId() {
        return id;
    }
}
