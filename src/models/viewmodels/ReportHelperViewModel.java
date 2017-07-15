package models.viewmodels;

import java.util.List;

/**
 * Created by boyanbonev on 10/01/2017.
 */

public class ReportHelperViewModel {
    private int id;
    private String name;
    private List<ChildViewModel> children;
    private List<GiftViewModel> gifts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildViewModel> getChildren() {
        return children;
    }

    public void setChildren(List<ChildViewModel> children) {
        this.children = children;
    }

    public List<GiftViewModel> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftViewModel> gifts) {
        this.gifts = gifts;
    }
}
