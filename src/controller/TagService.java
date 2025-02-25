package controller;

import com.db4o.query.Predicate;
import model.Tag;
import java.util.List;
import java.util.Optional;

public class TagService {
    private final DatabaseManager dbManager;

    public TagService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createTag(Tag tag) {
        if (getTagByName(tag.getName()).isPresent()) {
            throw new IllegalArgumentException("Tag name already exists!");
        }
        dbManager.save(tag);
    }

    public Optional<Tag> getTagById(String id) {
        return dbManager.findByQuery(new Predicate<Tag>() {
            @Override
            public boolean match(Tag tag) {
                return tag.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public Optional<Tag> getTagByName(String name) {
        return dbManager.findByQuery(new Predicate<Tag>() {
            @Override
            public boolean match(Tag tag) {
                return tag.getName().equalsIgnoreCase(name);
            }
        }).stream().findFirst();
    }

    public List<Tag> getAllTags() {
        return dbManager.find(Tag.class);
    }

    public void deleteTag(Tag tag) {
        dbManager.delete(tag);
    }
}
