public class RemoteControl {
    private CommandHistory history;
    // slots, etc.

    public RemoteControl(CommandHistory history){ this.history = history; }

    public void executeCommand(Command command) {
        command.execute();
        try {
            history.add(command.toSerializable());
        } catch (IOException e) {
            // log e tratamento (não falhar a execução por causa do log)
            e.printStackTrace();
        }
    }
}
