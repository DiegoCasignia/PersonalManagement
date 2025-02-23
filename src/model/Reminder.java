package model;

import java.util.Date;
import java.util.UUID;

public class Reminder {
    private String id;
    private String message;
    private Date dateTime;
    private boolean repeat;

    public Reminder(String message, Date dateTime, boolean repeat) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.dateTime = dateTime;
        this.repeat = repeat;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
