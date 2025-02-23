package model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String description;
    private Date dueDate;
    private int priority; // 1 = Low, 2 = Medium, 3 = High
    private boolean completed;
    private List<Tag> tags;
    private List<Reminder> reminders;

    public Task(String title, String description, Date dueDate, int priority, boolean completed) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    // Utility Methods
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }
}
