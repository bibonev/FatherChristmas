package models.viewmodels;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class ChildViewModel {

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

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
