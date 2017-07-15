package models.builders;

import javafx.scene.control.TextArea;
import models.viewmodels.ChildViewModel;
import models.viewmodels.GiftViewModel;
import models.viewmodels.ReportChildViewModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class ReportChildBuilder extends BaseBuilder implements IReportChildBuilder {

    public ReportChildBuilder(Connection _connection, TextArea _textArea) {
        super(_textArea, _connection);
    }

    @Override
    public ReportChildViewModel Build(int id) {
        String query = "SELECT Child.Cid, Child.Name, Child.Address, Present.Gid, Gift.Description\n" +
                "FROM Present " +
                "INNER JOIN Child " +
                "ON Present.Cid = Child.Cid " +
                "INNER JOIN Gift " +
                "ON Present.Gid = Gift.Gid " +
                "WHERE Child.Cid = " + id;
        ResultSet resultSet = Execute(query);

        ReportChildViewModel model = new ReportChildViewModel();

        try {
            while (resultSet.next()){
                if (model.getName() == null){
                    ChildViewModel child = new ChildViewModel();
                    GiftViewModel gift = new GiftViewModel();
                    List<GiftViewModel> gifts = new ArrayList<>();

                    child.setId(resultSet.getInt("Cid"));
                    child.setName(resultSet.getString("Name"));
                    child.setAddress(resultSet.getString("Address"));

                    gift.setId(resultSet.getInt("Gid"));
                    gift.setDescription(resultSet.getString("Description"));

                    gifts.add(gift);

                    model.setId(child.getId());
                    model.setName(child.getName());
                    model.setAddress(child.getAddress());
                    model.setGifts(gifts);

                } else {
                    GiftViewModel gift = new GiftViewModel();

                    gift.setId(resultSet.getInt("Gid"));
                    gift.setDescription(resultSet.getString("Description"));

                    model.getGifts().add(gift);
                }
            }

            //TODO:close result
            statement.close();
            return model;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
