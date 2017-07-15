package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ConnectionDetails;
import models.builders.IReportChildBuilder;
import models.builders.IReportHelperBuilder;
import models.builders.ReportChildBuilder;
import models.builders.ReportHelperBuilder;
import models.handlers.*;
import models.viewmodels.ReportChildViewModel;
import models.viewmodels.ReportHelperViewModel;

import java.sql.*;

public class Controller {

    @FXML private TextArea textArea;
    @FXML private TextField childId;
    @FXML private TableView tableView;
    @FXML private TextField helperId;

    private StringProperty mConnectionMessage;
    private Connection connection;

    private ICreateTablesHandler createTablesHandler;
    private IDropTablesHandler dropTablesHandler;
    private IInsertIntoTablesHandler insertIntoTablesHandler;
    private IReportChildBuilder reportChildBuilder;
    private IReportHelperBuilder reportHelperBuilder;

    public Controller(){
        this.mConnectionMessage = new SimpleStringProperty();
        this.setConnectionMessage("Press 'Connect' to establish database and create tables:");
    }

    public String getConnectionMessage() {
        return mConnectionMessage.get();
    }

    public StringProperty connectionMessageProperty() {
        return mConnectionMessage;
    }

    public void setConnectionMessage(String mConnectionMessage) {
        this.mConnectionMessage.set(mConnectionMessage);
    }

    public void makeConnection(ActionEvent actionEvent) {
        this.textArea.setText("");

        establishConnection();

        this.createTablesHandler = new CreateTablesHandler(this.connection, this.textArea);
        this.insertIntoTablesHandler = new InsertIntoTablesHandler(this.connection, this.textArea);

        this.createTablesHandler.Handle();
        this.insertIntoTablesHandler.Handle();

        closeConnection();

        if(this.textArea.getText().length() == 0) {
            this.textArea.setText("Connection, creation and insertion have been successful!");

        }
    }

    public void dropConnection(ActionEvent actionEvent) {
        this.textArea.setText("");

        establishConnection();

        this.dropTablesHandler = new DropTablesHandler(this.connection, this.textArea);
        this.dropTablesHandler.Handle();

        closeConnection();

        if(this.textArea.getText().length() == 0) {
            this.textArea.setText("Dropped tables successfully!");

        }
    }

    public void selectChildById(ActionEvent actionEvent) {
        this.textArea.setText("");

        if(!isNumeric(childId.getText())) {
            this.errorDialog();
            this.childId.setText("");
            return;
        }

        this.tableView.getItems().clear();
        this.tableView.getColumns().clear();

        establishConnection();

        this.reportChildBuilder = new ReportChildBuilder(this.connection, this.textArea);
        ReportChildViewModel result = this.reportChildBuilder.Build(Integer.parseInt(this.childId.getText()));


        this.tableView.setEditable(true);

        TableColumn idColumn = new TableColumn("Id");
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn addressColumn = new TableColumn("Address");
        TableColumn giftsColumn = new TableColumn("Gifts");

        idColumn.setCellValueFactory(new PropertyValueFactory<ReportChildViewModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ReportChildViewModel, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ReportChildViewModel, String>("address"));
        giftsColumn.setCellValueFactory(new PropertyValueFactory<ReportChildViewModel, String>("gifts"));

        ObservableList<ReportChildViewModel> data =
                FXCollections.observableArrayList(
                        result
                );

        this.tableView.setItems(data);
        this.tableView.getColumns().addAll(idColumn, nameColumn, addressColumn, giftsColumn);
        this.childId.setText("");

        closeConnection();

        if(this.textArea.getText().length() == 0) {
            this.textArea.setText("Report has been selected successfully!");

        }
    }

    public void selectHelperById(ActionEvent actionEvent) {
        this.textArea.setText("");

        if(!isNumeric(helperId.getText())) {
            this.errorDialog();
            this.helperId.setText("");
            return;
        }

        this.tableView.getItems().clear();
        this.tableView.getColumns().clear();

        establishConnection();

        this.reportHelperBuilder = new ReportHelperBuilder(this.connection, this.textArea);
        ReportHelperViewModel result = this.reportHelperBuilder.Build(Integer.parseInt(this.helperId.getText()));


        this.tableView.setEditable(true);

        TableColumn idColumn = new TableColumn("Id");
        TableColumn helperNameColumn = new TableColumn("Helper Name");
        TableColumn childrenColumn = new TableColumn("Children");
        TableColumn giftsColumn = new TableColumn("Gifts");

        idColumn.setCellValueFactory(new PropertyValueFactory<ReportHelperViewModel, String>("id"));
        helperNameColumn.setCellValueFactory(new PropertyValueFactory<ReportHelperViewModel, String>("name"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<ReportHelperViewModel, String>("children"));
        giftsColumn.setCellValueFactory(new PropertyValueFactory<ReportHelperViewModel, String>("gifts"));

        ObservableList<ReportHelperViewModel> data =
                FXCollections.observableArrayList(
                        result
                );

        this.tableView.setItems(data);
        this.tableView.getColumns().addAll(idColumn, helperNameColumn, childrenColumn, giftsColumn);
        this.helperId.setText("");

        closeConnection();

        if(this.textArea.getText().length() == 0) {
            this.textArea.setText("Report has been selected successfully!");

        }
    }

    private void establishConnection() {
        try{
            Class.forName("org.postgresql.Driver");

            this.connection = DriverManager.getConnection(
                    ConnectionDetails.URL,
                    ConnectionDetails.Username,
                    ConnectionDetails.Password);
        } catch (Exception e){
            e.printStackTrace();
            this.textArea.setText(e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException se){
            this.textArea.setText(se.getMessage());
        }
    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong Input!");
        alert.setContentText("Ooops, you have entered a character that is not a number!");

        alert.showAndWait();
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
