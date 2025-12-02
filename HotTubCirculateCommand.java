import java.util.HashMap;

public class HotTubCirculateCommand implements Command {

    private HotTub tub;

    public HotTubCirculateCommand(HotTub tub) {
        this.tub = tub;
    }

    @Override
    public void execute() {
        tub.circulate();
    }

    @Override
    public void undo() {
        tub.jetsOff();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "HotTubCirculate";
        s.deviceId = tub.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
