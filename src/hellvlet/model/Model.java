package hellvlet.model;

import hellvlet.annotation.Column;

public abstract class Model {

    @Column
    public int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
