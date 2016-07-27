package hellvlet.model;

import java.util.Comparator;

public abstract class Model implements Comparator<Model> {

    private int id = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compare(Model o1, Model o2) {
        return o1.getId() == o2.getId() ? 0 : (o1.getId() > o2.getId() ? 1 : 0);
    }
}
