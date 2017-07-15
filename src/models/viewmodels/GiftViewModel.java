package models.viewmodels;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class GiftViewModel {
    private int id;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        this.description = _description;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
