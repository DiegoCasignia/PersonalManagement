package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {
    private String id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String location;
    private boolean reminderEnabled;
    private List<Tag> tags;
    private List<Reminder> reminders;

    public Event(String title, String description, Date startDate, Date endDate, String location, boolean reminderEnabled) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.reminderEnabled = reminderEnabled;
        this.tags = new ArrayList<>();
        this.reminders = new ArrayList<>();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isReminderEnabled() {
        return reminderEnabled;
    }

    public void setReminderEnabled(boolean reminderEnabled) {
        this.reminderEnabled = reminderEnabled;
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
