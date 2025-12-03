import java.util.HashMap;

public class HotTubSetTemperatureCommand implements Command {

    private HotTub tub;
    private int temperature;

    public HotTubSetTemperatureCommand(HotTub tub, int temperature) {
        this.tub = tub;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        tub.setTemperature(temperature);
    }

    @Override
    public void undo() {
        tub.setTemperature(25);
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "HotTubSetTemperature";
        s.deviceId = tub.getId();

        s.params = new HashMap<>();
        s.params.put("temperature", temperature);

        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
