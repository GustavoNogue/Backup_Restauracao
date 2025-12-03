import java.util.Map;

public class SerializableCommand {
    public String commandType;
    public String deviceId;
    public Map<String,Object> params;
    public long timestamp;

    // Construtor default necessário para bibliotecas de desserialização (Jackson)
    public SerializableCommand() {}

    public SerializableCommand(String commandType, String deviceId, Map<String,Object> params, long timestamp) {
        this.commandType = commandType;
        this.deviceId = deviceId;
        this.params = params;
        this.timestamp = timestamp;
    }
}
