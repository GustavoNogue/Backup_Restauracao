import java.util.HashMap;

public class GarageDoorStopCommand implements Command {

    private GarageDoor door;

    public GarageDoorStopCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.stop();
    }

    @Override
    public void undo() {
        door.down();
    }

    @Override
    public SerializableCommand toSerializable() {
        SerializableCommand s = new SerializableCommand();
        s.commandType = "GarageDoorStop";
        s.deviceId = door.getId();
        s.params = new HashMap<>();
        s.timestamp = System.currentTimeMillis();
        return s;
    }
}
