import java.util.HashMap;

public class SecurityDisarmCommand implements Command {

    private SecurityControl sec;

    public SecurityDisarmCommand(SecurityControl sec) {
        this.sec = sec;
    }

    @Override
    public void execute() {
        sec.disarm();
    }

    @Override
    public void undo() {
        sec.arm();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SecurityDisarm";
        s.deviceId = sec.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
