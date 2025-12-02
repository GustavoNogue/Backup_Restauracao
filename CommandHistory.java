public class CommandHistory {
    private List<SerializableCommand> memory = new ArrayList<>();
    private Path file = Paths.get("command-history.json");
    private ObjectMapper mapper = new ObjectMapper();

    // chamado pelo invoker sempre que um comando for executado
    public synchronized void add(SerializableCommand s) throws IOException {
        memory.add(s);
        // persistir: reescrever o arquivo com a lista completa
        mapper.writeValue(file.toFile(), memory);
    }

    public synchronized List<SerializableCommand> loadAll() throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        SerializableCommand[] arr = mapper.readValue(file.toFile(), SerializableCommand[].class);
        memory = new ArrayList<>(Arrays.asList(arr));
        return memory;
    }
}
