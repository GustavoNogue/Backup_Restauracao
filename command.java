public interface Command {
    void execute();
    void undo(); // pode lançar UnsupportedOperationException se não suportar
    SerializableCommand toSerializable(); // método para transformar em DTO para salvar
}