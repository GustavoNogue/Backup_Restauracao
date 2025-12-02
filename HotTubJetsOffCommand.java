import java.util.HashMap;

public class HotTubJetsOffCommand implements Command {

    private HotTub tub;

    public HotTubJetsOffCommand(HotTub tub) {
        this.tub = tub;
    }

    @Override
    public void execute() {
        tub.jetsOff();
    }

    @Override
    public void undo() {
        tub.jetsOn();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "HotTubJetsOff";
        s.deviceId = tub.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
