import java.util.HashMap;

public class SoundSetRadioCommand implements Command {

    private Sound sound;

    public SoundSetRadioCommand(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void execute() {
        sound.setRadio();
    }

    @Override
    public void undo() {
        sound.off();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SoundSetRadio";
        s.deviceId = sound.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
