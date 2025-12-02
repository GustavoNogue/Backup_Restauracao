import java.util.HashMap;

public class CeilingFanSetSpeedCommand implements Command {

    private CeilingFan fan;
    private CeilingFan.Speed speed;

    public CeilingFanSetSpeedCommand(CeilingFan fan, CeilingFan.Speed speed) {
        this.fan = fan;
        this.speed = speed;
    }

    @Override
    public void execute() {
        switch (speed) {
            case HIGH: fan.high(); break;
            case MEDIUM: fan.medium(); break;
            case LOW: fan.low(); break;
            case OFF: fan.off(); break;
        }
    }

    @Override
    public void undo() {
        fan.off();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "CeilingFanSetSpeed";
        s.deviceId = fan.getId();

        s.params = new HashMap<>();
        s.params.put("speed", speed.name());

        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
