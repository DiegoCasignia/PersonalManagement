package model;

import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private UserSettings settings;
    private List<Contact> contacts;
    private List<Task> tasks;
    private List<Note> notes;
    private List<Event> events;

    public User(String name, String email, String password, UserSettings settings) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.settings = settings;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // Utility Methods
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
