public class Light {

    private String id;

    private boolean on = false;

    public Light(String id){ this.id = id; }

    public void on(){ on = true; System.out.println(id + " Light on"); }

    public void off(){ on = false; System.out.println(id + " Light off"); }

    public boolean isOn(){ return on; }
    
    public String getId(){ return id; }
}
