import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactBusinessLogic {
    private ContactServer server;

    public ContactBusinessLogic() {
        this.server = new ContactServer();
    }

    // VIEW
    public ResultSet select_name(String name) throws SQLException {
        return server.select_name(name);
    }

    // ADD
    public Boolean insert(String name, String location, String phone) {
        return server.insert(name, location, phone);
    }

    // UPDATE
    public void update_name(int id, String name, String location, String phone) {
        server.update_name(id, name, location, phone);
    }

    // DELETE
    public Boolean delete(int id) {
        return server.delete(id);
    }
}