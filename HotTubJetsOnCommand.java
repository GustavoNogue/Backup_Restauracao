import java.util.HashMap;

public class HotTubJetsOnCommand implements Command {

    private HotTub tub;

    public HotTubJetsOnCommand(HotTub tub) {
        this.tub = tub;
    }

    @Override
    public void execute() {
        tub.jetsOn();
    }

    @Override
    public void undo() {
        tub.jetsOff();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "HotTubJetsOn";
        s.deviceId = tub.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
