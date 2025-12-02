import java.util.HashMap;

public class TVSetChannelCommand implements Command {

    private TV tv;
    private int channel;

    public TVSetChannelCommand(TV tv, int channel) {
        this.tv = tv;
        this.channel = channel;
    }

    @Override
    public void execute() {
        tv.setInputChannel(channel);
    }

    @Override
    public void undo() {
        tv.setInputChannel(1);
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "TVSetChannel";
        s.deviceId = tv.getId();

        s.params = new HashMap<>();
        s.params.put("channel", channel);

        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
