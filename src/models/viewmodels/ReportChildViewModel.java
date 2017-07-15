package models.viewmodels;

import java.util.List;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class ReportChildViewModel {

    private int id;

    private String name;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String _address) {
        this.address = _address;
    }

    private List<GiftViewModel> gifts;

    public List<GiftViewModel> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftViewModel> gifts) {
        this.gifts = gifts;
    }

    @Override
    public String toString() {
        return "ReportChildViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gifts=" + gifts +
                '}';
    }
}
