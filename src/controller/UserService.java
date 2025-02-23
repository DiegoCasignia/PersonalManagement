package controller;

import com.db4o.query.Predicate;
import model.User;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final DatabaseManager dbManager;

    public UserService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createUser(User user) {
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }
        dbManager.save(user);
    }

    public Optional<User> getUserById(String id) {
        return dbManager.findByQuery(new Predicate<User>() {
            @Override
            public boolean match(User user) {
                return user.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public Optional<User> getUserByEmail(String email) {
        return dbManager.findByQuery(new Predicate<User>() {
            @Override
            public boolean match(User user) {
                return user.getEmail().equalsIgnoreCase(email);
            }
        }).stream().findFirst();
    }

    public List<User> getAllUsers() {
        return dbManager.find(User.class);
    }

    public void deleteUser(User user) {
        dbManager.delete(user);
    }
}
