package hellvlet.model;

import java.util.Comparator;

public abstract class Model {

    private int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
