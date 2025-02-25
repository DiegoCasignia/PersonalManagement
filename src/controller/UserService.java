package controller;

import com.db4o.query.Predicate;
import model.*;
import java.util.ArrayList;
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

    public void updateUser(User user) {
        dbManager.save(user);
    }

    // Método para agregar un contacto a un usuario
    public void addContactToUser(String userId, Contact contact) {
        Optional<User> optionalUser = getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getContacts() == null) {
                user.setContacts(new ArrayList<>());
            }
            user.addContact(contact);
            dbManager.save(user);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }

    // Método para agregar una tarea a un usuario
    public void addTaskToUser(String userId, Task task) {
        Optional<User> optionalUser = getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getTasks() == null) {
                user.setTasks(new ArrayList<>());
            }
            user.addTask(task);
            dbManager.save(user);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }

    // Método para agregar una nota a un usuario
    public void addNoteToUser(String userId, Note note) {
        Optional<User> optionalUser = getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getNotes() == null) {
                user.setNotes(new ArrayList<>());
            }
            user.addNote(note);
            dbManager.save(user);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }

    // Método para agregar un evento a un usuario
    public void addEventToUser(String userId, Event event) {
        Optional<User> optionalUser = getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getEvents() == null) {
                user.setEvents(new ArrayList<>());
            }
            user.addEvent(event);
            dbManager.save(user);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }
}
