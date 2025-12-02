import java.util.HashMap;

public class TVSetVolumeCommand implements Command {

    private TV tv;
    private int volume;

    public TVSetVolumeCommand(TV tv, int volume) {
        this.tv = tv;
        this.volume = volume;
    }

    @Override
    public void execute() {
        tv.setVolume(volume);
    }

    @Override
    public void undo() {
        tv.setVolume(10);
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "TVSetVolume";
        s.deviceId = tv.getId();

        s.params = new HashMap<>();
        s.params.put("volume", volume);

        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
