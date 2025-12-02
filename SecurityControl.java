public class SecurityControl {

    private String id;
    private boolean armed = false;

    public SecurityControl(String id) {
        this.id = id;
    }

    public void arm() {
        armed = true;
        System.out.println(id + " Security System ARMED");
    }

    public void disarm() {
        armed = false;
        System.out.println(id + " Security System DISARMED");
    }

    public boolean isArmed() {
        return armed;
    }

    public String getId() {
        return id;
    }
}
