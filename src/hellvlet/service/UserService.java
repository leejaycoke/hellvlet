package hellvlet.service;

import hellvlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService extends BaseService<User> {

    private final static List<User> mUsers = new ArrayList<>();

    public UserService() {
        super(mUsers);
    }

    public User findByAccount(String account) {
        List<User> items = mData.stream()
                .filter(item -> item.getAccount().equals(account))
                .collect(Collectors.toList());
        return items.size() == 1 ? items.get(0) : null;
    }

}
