import java.util.HashMap;

public class SoundSetCdCommand implements Command {

    private Sound sound;

    public SoundSetCdCommand(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void execute() {
        sound.setCd();
    }

    @Override
    public void undo() {
        sound.off();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SoundSetCd";
        s.deviceId = sound.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
