public class RecoverySystem {
    CommandFactory factory;
    CommandHistory history;

    public void recover() {
        List<SerializableCommand> list = history.loadAll();
        for (SerializableCommand sc : list) {
            Command c = factory.createFrom(sc);
            c.execute();
        }
    }
}
