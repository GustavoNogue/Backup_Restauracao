public class CeilingFan {

    public enum Speed { OFF, LOW, MEDIUM, HIGH }

    private String id;
    private Speed speed = Speed.OFF;

    public CeilingFan(String id) {
        this.id = id;
    }

    public void high() {
        speed = Speed.HIGH;
        System.out.println(id + " CeilingFan set to HIGH");
    }

    public void medium() {
        speed = Speed.MEDIUM;
        System.out.println(id + " CeilingFan set to MEDIUM");
    }

    public void low() {
        speed = Speed.LOW;
        System.out.println(id + " CeilingFan set to LOW");
    }

    public void off() {
        speed = Speed.OFF;
        System.out.println(id + " CeilingFan set to OFF");
    }

    public Speed getSpeed() {
        return speed;
    }

    public String getId() {
        return id;
    }
}
