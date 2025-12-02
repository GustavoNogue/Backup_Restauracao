import java.util.Map;
import java.util.Objects;

/**
 * CommandFactory: recria objetos Command a partir de SerializableCommand (lido do JSON).
 * Espera um deviceRegistry com deviceId -> deviceInstance.
 */
public class CommandFactory {

    private final Map<String, Object> deviceRegistry; // deviceId -> deviceInstance

    public CommandFactory(Map<String, Object> deviceRegistry) {
        this.deviceRegistry = Objects.requireNonNull(deviceRegistry, "deviceRegistry cannot be null");
    }

    public Command createFrom(SerializableCommand s) {
        if (s == null) throw new IllegalArgumentException("SerializableCommand cannot be null");
        if (s.commandType == null) throw new IllegalArgumentException("commandType is null in SerializableCommand");

        switch (s.commandType) {
            // LIGHT
            case "LightOn": {
                Light l = getDevice(s.deviceId, Light.class);
                return new LightOnCommand(l);
            }
            case "LightOff": {
                Light l = getDevice(s.deviceId, Light.class);
                return new LightOffCommand(l);
            }

            // CEILING FAN (set speed)
            case "CeilingFanSetSpeed": {
                CeilingFan fan = getDevice(s.deviceId, CeilingFan.class);
                String sp = getStringParam(s, "speed");
                CeilingFan.Speed speed = CeilingFan.Speed.valueOf(sp);
                return new CeilingFanSetSpeedCommand(fan, speed);
            }

            // GARAGE DOOR
            case "GarageDoorUp": {
                GarageDoor g = getDevice(s.deviceId, GarageDoor.class);
                return new GarageDoorUpCommand(g);
            }
            case "GarageDoorDown": {
                GarageDoor g = getDevice(s.deviceId, GarageDoor.class);
                return new GarageDoorDownCommand(g);
            }
            case "GarageDoorStop": {
                GarageDoor g = getDevice(s.deviceId, GarageDoor.class);
                return new GarageDoorStopCommand(g);
            }

            // TV
            case "TVOn": {
                TV tv = getDevice(s.deviceId, TV.class);
                return new TVOnCommand(tv);
            }
            case "TVOff": {
                TV tv = getDevice(s.deviceId, TV.class);
                return new TVOffCommand(tv);
            }
            case "TVSetChannel": {
                TV tv = getDevice(s.deviceId, TV.class);
                int ch = getIntParam(s, "channel");
                return new TVSetChannelCommand(tv, ch);
            }
            case "TVSetVolume": {
                TV tv = getDevice(s.deviceId, TV.class);
                int vol = getIntParam(s, "volume");
                return new TVSetVolumeCommand(tv, vol);
            }

            // SOUND
            case "SoundSetCd": {
                Sound sound = getDevice(s.deviceId, Sound.class);
                return new SoundSetCdCommand(sound);
            }
            case "SoundSetDvd": {
                Sound sound = getDevice(s.deviceId, Sound.class);
                return new SoundSetDvdCommand(sound);
            }
            case "SoundSetRadio": {
                Sound sound = getDevice(s.deviceId, Sound.class);
                return new SoundSetRadioCommand(sound);
            }
            case "SoundSetVolume": {
                Sound sound = getDevice(s.deviceId, Sound.class);
                int vol = getIntParam(s, "volume");
                return new SoundSetVolumeCommand(sound, vol);
            }

            // HOT TUB
            case "HotTubCirculate": {
                HotTub tub = getDevice(s.deviceId, HotTub.class);
                return new HotTubCirculateCommand(tub);
            }
            case "HotTubJetsOn": {
                HotTub tub = getDevice(s.deviceId, HotTub.class);
                return new HotTubJetsOnCommand(tub);
            }
            case "HotTubJetsOff": {
                HotTub tub = getDevice(s.deviceId, HotTub.class);
                return new HotTubJetsOffCommand(tub);
            }
            case "HotTubSetTemperature": {
                HotTub tub = getDevice(s.deviceId, HotTub.class);
                int temp = getIntParam(s, "temperature");
                return new HotTubSetTemperatureCommand(tub, temp);
            }

            // SECURITY CONTROL
            case "SecurityArm": {
                SecurityControl sec = getDevice(s.deviceId, SecurityControl.class);
                return new SecurityArmCommand(sec);
            }
            case "SecurityDisarm": {
                SecurityControl sec = getDevice(s.deviceId, SecurityControl.class);
                return new SecurityDisarmCommand(sec);
            }

            default:
                throw new IllegalArgumentException("Unknown command: " + s.commandType);
        }
    }

    /**
     * Helper to fetch and cast device from registry, with nice error message.
     */
    @SuppressWarnings("unchecked")
    private <T> T getDevice(String deviceId, Class<T> clazz) {
        if (deviceId == null) throw new IllegalArgumentException("deviceId is null for expected device type " + clazz.getSimpleName());
        Object dev = deviceRegistry.get(deviceId);
        if (dev == null) {
            throw new IllegalArgumentException("No device registered with id: " + deviceId + " (expected " + clazz.getSimpleName() + ")");
        }
        if (!clazz.isInstance(dev)) {
            throw new IllegalArgumentException("Device with id: " + deviceId + " is not of type " + clazz.getSimpleName() + ". Actual: " + dev.getClass().getSimpleName());
        }
        return (T) dev;
    }

    /**
     * Extract integer parameter (accepts Number or String that can be parsed).
     */
    private int getIntParam(SerializableCommand s, String name) {
        if (s.params == null || !s.params.containsKey(name)) {
            throw new IllegalArgumentException("Missing integer parameter '" + name + "' for command " + s.commandType);
        }
        Object o = s.params.get(name);
        if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof String) {
            try {
                return Integer.parseInt((String) o);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Parameter '" + name + "' is not a valid integer: " + o);
            }
        } else {
            throw new IllegalArgumentException("Parameter '" + name + "' has unsupported type: " + (o == null ? "null" : o.getClass().getSimpleName()));
        }
    }

    /**
     * Extract string parameter.
     */
    private String getStringParam(SerializableCommand s, String name) {
        if (s.params == null || !s.params.containsKey(name)) {
            throw new IllegalArgumentException("Missing string parameter '" + name + "' for command " + s.commandType);
        }
        Object o = s.params.get(name);
        if (o instanceof String) {
            return (String) o;
        } else {
            return String.valueOf(o);
        }
    }
}
