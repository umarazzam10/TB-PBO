import java.sql.SQLException;

public interface CRUD {
    void viewData() throws SQLException;
    void addData() throws SQLException;
    void updateData() throws SQLException;
    void deleteData() throws SQLException;
}
