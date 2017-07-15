package models.handlers;

import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class CreateTablesHandler extends BaseHandler implements ICreateTablesHandler {

    public CreateTablesHandler(Connection _connection, TextArea _textArea)
    {
        super(_textArea, _connection);
    }

    @Override
    public void Handle() {
        String createChildQuery =
                "CREATE TABLE Child (" +
                "Cid Integer," +
                "Name Text NOT NULL," +
                "Address Text NOT NULL," +
                "PRIMARY KEY (Cid)" + ")";

        String createSantasClouseHelpersQuery =
                "CREATE TABLE SantasLittleHelper (" +
                "Slhid Integer," + "Name Text NOT NULL," +
                "PRIMARY KEY (Slhid)" +
                ")";

        String createGiftQuery =
                "CREATE TABLE Gift (" +
                "Gid Integer," +
                "Description Text NOT NULL," +
                "PRIMARY KEY (Gid)" +
                ")";

        String createPresentQuery =
                "CREATE TABLE Present (" +
                "Gid Integer," +
                        "Cid Integer," +
                        "Slhid Integer," +
                        "FOREIGN KEY (Gid) REFERENCES Gift(Gid)," +
                        "FOREIGN KEY (Cid) REFERENCES Child(Cid)," +
                        "FOREIGN KEY (Slhid) REFERENCES SantasLittleHelper(Slhid)" +
                ")";

        Execute(createChildQuery, createGiftQuery, createSantasClouseHelpersQuery, createPresentQuery);
    }
}
