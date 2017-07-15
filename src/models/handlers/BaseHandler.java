package models.handlers;

import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class BaseHandler {
    protected Connection connection = null;
    protected Statement statement = null;
    protected TextArea textArea;

    public BaseHandler(TextArea _textArea, Connection _connection) {
        this.textArea = _textArea;
        this.connection = _connection;
    }

    public void Execute(String queryT1, String queryT2, String queryT3, String queryT4){
        try {
            statement = connection.createStatement();
            if(queryT1 != null) {
                statement.executeUpdate(queryT1);
            }
            if(queryT2 != null) {
                statement.executeUpdate(queryT2);
            }
            if(queryT3 != null) {
                statement.executeUpdate(queryT3);
            }
            if(queryT4 != null) {
                statement.executeUpdate(queryT4);
            }
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
            this.textArea.setText(se.getMessage());
        }
    }

    public void Execute(String queryT1) {
        try {
            statement = connection.createStatement();
            if(queryT1 != null) {
                statement.executeUpdate(queryT1);
            }
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
            this.textArea.setText(se.getMessage());
        }
    }
}
