public class GarageDoor {

    private String id;

    public GarageDoor(String id) {
        this.id = id;
    }

    public void up() {
        System.out.println(id + " GarageDoor going UP");
    }

    public void down() {
        System.out.println(id + " GarageDoor going DOWN");
    }

    public void stop() {
        System.out.println(id + " GarageDoor STOPPED");
    }

    public String getId() {
        return id;
    }
}
