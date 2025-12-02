import java.util.HashMap;

public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "LightOff";
        s.deviceId = light.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
