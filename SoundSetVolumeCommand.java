import java.util.HashMap;

public class SoundSetVolumeCommand implements Command {

    private Sound sound;
    private int volume;

    public SoundSetVolumeCommand(Sound sound, int volume) {
        this.sound = sound;
        this.volume = volume;
    }

    @Override
    public void execute() {
        sound.setVolume(volume);
    }

    @Override
    public void undo() {
        sound.setVolume(5);
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SoundSetVolume";
        s.deviceId = sound.getId();

        s.params = new HashMap<>();
        s.params.put("volume", volume);

        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
