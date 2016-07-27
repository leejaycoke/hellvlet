package hellvlet.service;

import hellvlet.model.BaseModel;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService<T extends BaseModel> {

    public final List<T> mData;

    public BaseService(List<T> items) {
        mData = items;
    }

    public List<T> getAll() {
        return mData;
    }

    public void create(T item) {
        mData.add(item);
    }

    public T findById(int id) {
        List<T> items = mData.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
        return items.size() == 1 ? items.get(0) : null;
    }

    public void deleteById(int id) {
        // pass
    }
}