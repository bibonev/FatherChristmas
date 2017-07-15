package models.handlers;

import javafx.scene.control.TextArea;

import java.sql.Connection;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class DropTablesHandler extends BaseHandler implements IDropTablesHandler{

    public DropTablesHandler(Connection _connection, TextArea _textArea)
    {
        super(_textArea, _connection);
    }

    @Override
    public void Handle() {
        String dropChildQuery = "DROP TABLE Child";
        String dropSantasClouseHelpersQuery =  "DROP TABLE SantasLittleHelper";
        String dropGiftQuery = "DROP TABLE Gift";
        String dropPresentQuery = "DROP TABLE Present";

        Execute(dropPresentQuery, dropChildQuery, dropGiftQuery, dropSantasClouseHelpersQuery);
    }
}
