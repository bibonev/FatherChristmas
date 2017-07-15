package models.builders;

import javafx.scene.control.TextArea;
import models.viewmodels.ChildViewModel;
import models.viewmodels.GiftViewModel;
import models.viewmodels.ReportHelperViewModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boyanbonev on 10/01/2017.
 */
public class ReportHelperBuilder extends BaseBuilder implements IReportHelperBuilder {

    public ReportHelperBuilder(Connection _connection, TextArea _textArea) {
        super(_textArea, _connection);
    }

    @Override
    public ReportHelperViewModel Build(int id) {
        String query =
                "SELECT Present.Slhid, SantasLittleHelper.Name AS HelperName, Child.Name AS ChildName, Child.Address, Gift.Gid, Gift.Description " +
                "From Present " +
                "Inner join Child " +
                "On Present.Cid = Child.Cid " +
                "Inner join Gift " +
                "On Present.Gid = Gift.Gid " +
                "Inner join SantasLittleHelper " +
                "On Present.Slhid = SantasLittleHelper.Slhid " +
                "Where SantasLittleHelper.Slhid = " + id;

        ResultSet resultSet = Execute(query);
        ReportHelperViewModel model = new ReportHelperViewModel();

        try{
            while (resultSet.next()){
                if(model.getName() == null){
                    model.setId(resultSet.getInt("Slhid"));
                    model.setName(resultSet.getString("HelperName"));

                    ChildViewModel child = new ChildViewModel();
                    List<ChildViewModel> children = new ArrayList<>();
                    child.setName(resultSet.getString("ChildName"));
                    child.setAddress(resultSet.getString("Address"));
                    children.add(child);
                    model.setChildren(children);

                    GiftViewModel gift = new GiftViewModel();
                    List<GiftViewModel> gifts = new ArrayList<>();
                    gift.setId(resultSet.getInt("Gid"));
                    gift.setDescription(resultSet.getString("Description"));
                    gifts.add(gift);
                    model.setGifts(gifts);
                }else {
                    ChildViewModel child = new ChildViewModel();
                    child.setName(resultSet.getString("ChildName"));
                    child.setAddress(resultSet.getString("Address"));
                    model.getChildren().add(child);

                    GiftViewModel gift = new GiftViewModel();
                    gift.setId(resultSet.getInt("Gid"));
                    gift.setDescription(resultSet.getString("Description"));
                    model.getGifts().add(gift);
                }
            }
            statement.close();
            return model;
        }catch (SQLException se){
            se.printStackTrace();
        }

        return null;
    }
}
