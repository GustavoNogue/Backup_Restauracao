import java.io.IOException;
import java.util.List;

public class RecoverySystem {
    CommandFactory factory;
    CommandHistory history;

    public void recover() throws IOException {
        List<SerializableCommand> list = history.loadAll();
        for (SerializableCommand sc : list) {
            Command c = factory.createFrom(sc);
            c.execute();
        }
    }
}
