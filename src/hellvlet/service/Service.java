package hellvlet.service;

import hellvlet.model.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Service<T extends Model> {

    private final List<T> mData;

    Service(List<T> items) {
        mData = items;
    }

    public List<T> getAll() {
        return mData;
    }

    public void create(T item) {
        item.setId(getNextId());
        mData.add(item);
    }

    public T get(int id) {
        List<T> items = mData.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
        return items.size() == 1 ? items.get(0) : null;
    }

    public boolean delete(int id) {
        return mData.removeIf(item -> item.getId() == id);
    }

    int getNextId() {
        if (mData.size() > 0) {
            return Collections.max(mData, new ModelComparator()).getId() + 1;
        }
        return 1;
    }

    private class ModelComparator implements Comparator<Model> {

        @Override
        public int compare(Model o1, Model o2) {
            return o1.getId() == o2.getId() ? 0 : (o1.getId() > o2.getId() ? 1 : 0);
        }
    }

}