import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHistory {
    private List<SerializableCommand> memory = new ArrayList<>();
    private Path file;
    private ObjectMapper mapper = new ObjectMapper();

    public CommandHistory(String path) {
        this.file = Paths.get(path);
    }

    public CommandHistory() {
        this("command-history.json");
    }

    // chamado pelo invoker sempre que um comando for executado
    public synchronized void add(SerializableCommand s) throws IOException {
        memory.add(s);
        // persistir: reescrever o arquivo com a lista completa
        // cria diretório se necessário
        if (file.getParent() != null && !Files.exists(file.getParent())) {
            Files.createDirectories(file.getParent());
        }
        mapper.writeValue(file.toFile(), memory);
    }

    public synchronized List<SerializableCommand> loadAll() throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        SerializableCommand[] arr = mapper.readValue(file.toFile(), SerializableCommand[].class);
        memory = new ArrayList<>(Arrays.asList(arr));
        return memory;
    }
}
