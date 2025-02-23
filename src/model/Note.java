package model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String content;
    private final Date creationDate;
    private List<Tag> tags;

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.creationDate = new Date(); // Automatically set to current date
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    // Utility Method
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
