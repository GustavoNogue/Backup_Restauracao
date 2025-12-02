import java.util.HashMap;

public class SecurityArmCommand implements Command {

    private SecurityControl sec;

    public SecurityArmCommand(SecurityControl sec) {
        this.sec = sec;
    }

    @Override
    public void execute() {
        sec.arm();
    }

    @Override
    public void undo() {
        sec.disarm();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "SecurityArm";
        s.deviceId = sec.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
