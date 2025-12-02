import java.util.HashMap;

public class SoundSetDvdCommand implements Command {

    private Sound sound;

    public SoundSetDvdCommand(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void execute() {
        sound.setDvd();
    }

    @Override
    public void undo() {
        sound.off();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SoundSetDvd";
        s.deviceId = sound.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
