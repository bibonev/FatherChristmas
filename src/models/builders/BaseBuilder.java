package models.builders;

import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class BaseBuilder {
    protected Connection connection = null;
    protected Statement statement = null;
    protected TextArea textArea;

    public BaseBuilder(TextArea _textArea, Connection _connection) {
        this.textArea = _textArea;
        this.connection = _connection;
    }

    public ResultSet Execute(String query){
        try {
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException se){
            se.printStackTrace();
            this.textArea.setText(se.getMessage());
        }

        return null;
    }
}
