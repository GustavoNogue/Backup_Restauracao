import java.io.IOException;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws Exception {
        // ---------- Inicialização (primeira execução) ----------
        // 1) criar devices (receivers)
        Light livingLight = new Light("living-light");
        TV livingTV = new TV("living-tv");
        CeilingFan bedroomFan = new CeilingFan("bedroom-fan");
        GarageDoor garage = new GarageDoor("main-garage");
        Sound sound = new Sound("living-sound");
        HotTub hotTub = new HotTub("backyard-hottub");
        SecurityControl security = new SecurityControl("home-security");

        // 2) montar deviceRegistry (id -> instance)
        Map<String,Object> deviceRegistry = new HashMap<>();
        deviceRegistry.put(livingLight.getId(), livingLight);
        deviceRegistry.put(livingTV.getId(), livingTV);
        deviceRegistry.put(bedroomFan.getId(), bedroomFan);
        deviceRegistry.put(garage.getId(), garage);
        deviceRegistry.put(sound.getId(), sound);
        deviceRegistry.put(hotTub.getId(), hotTub);
        deviceRegistry.put(security.getId(), security);

        // 3) criar CommandHistory e RemoteControl
        CommandHistory history = new CommandHistory("command-history.json"); // ou como for seu construtor
        RemoteControl remote = new RemoteControl(history);

        // 4) executar alguns comandos
        remote.executeCommand(new LightOnCommand(livingLight));
        remote.executeCommand(new TVOnCommand(livingTV));
        remote.executeCommand(new TVSetChannelCommand(livingTV, 7));
        remote.executeCommand(new CeilingFanSetSpeedCommand(bedroomFan, CeilingFan.Speed.HIGH));
        remote.executeCommand(new SoundSetCdCommand(sound));
        remote.executeCommand(new HotTubSetTemperatureCommand(hotTub, 38));
        remote.executeCommand(new SecurityArmCommand(security));

        System.out.println("\n--- Comandos executados e salvos ---");
        // (Opcional) imprimir estado atual de alguns devices
        System.out.println("TV isOn: " + livingTV.isOn() + ", channel: " + livingTV.getChannel());
        System.out.println("CeilingFan speed: " + bedroomFan.getSpeed());
        System.out.println("Security armed: " + security.isArmed());

        // ---------- Simular pane elétrica e nova inicialização ----------
        System.out.println("\n>>> Simulando pane e reinício do sistema...");

        // Simula novo processo: criar NOVAS instâncias dos devices (estado reset)
        Light livingLight2 = new Light("living-light");
        TV livingTV2 = new TV("living-tv");
        CeilingFan bedroomFan2 = new CeilingFan("bedroom-fan");
        GarageDoor garage2 = new GarageDoor("main-garage");
        Sound sound2 = new Sound("living-sound");
        HotTub hotTub2 = new HotTub("backyard-hottub");
        SecurityControl security2 = new SecurityControl("home-security");

        Map<String,Object> deviceRegistry2 = new HashMap<>();
        deviceRegistry2.put(livingLight2.getId(), livingLight2);
        deviceRegistry2.put(livingTV2.getId(), livingTV2);
        deviceRegistry2.put(bedroomFan2.getId(), bedroomFan2);
        deviceRegistry2.put(garage2.getId(), garage2);
        deviceRegistry2.put(sound2.getId(), sound2);
        deviceRegistry2.put(hotTub2.getId(), hotTub2);
        deviceRegistry2.put(security2.getId(), security2);

        // Reusar o mesmo arquivo de history: criar novo CommandHistory usando mesmo path
        CommandHistory historyReload = new CommandHistory("command-history.json");
        List<SerializableCommand> saved = historyReload.loadAll();

        // CommandFactory usa o novo registry para criar comandos ligados aos novos objetos
        CommandFactory factory = new CommandFactory(deviceRegistry2);

        System.out.println("\n--- Restaurando estado a partir do history.json ---");
        for (SerializableCommand sc : saved) {
            try {
                Command c = factory.createFrom(sc);
                c.execute(); // re-executa a ação na mesma ordem
            } catch (Exception ex) {
                System.err.println("Erro ao recriar/executar comando: " + sc.commandType + " para device " + sc.deviceId + " -> " + ex.getMessage());
            }
        }

        System.out.println("\n--- Estado após restauração ---");
        System.out.println("TV isOn: " + livingTV2.isOn() + ", channel: " + livingTV2.getChannel());
        System.out.println("CeilingFan speed: " + bedroomFan2.getSpeed());
        System.out.println("Security armed: " + security2.isArmed());
    }
}
