import java.util.HashMap;

public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "LightOn";
        s.deviceId = light.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
