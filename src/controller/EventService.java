package controller;

import com.db4o.query.Predicate;
import model.Event;
import java.util.List;
import java.util.Optional;

public class EventService {
    private final DatabaseManager dbManager;

    public EventService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createEvent(Event event) {
        dbManager.save(event);
    }

    public Optional<Event> getEventById(String id) {
        return dbManager.findByQuery(new Predicate<Event>() {
            @Override
            public boolean match(Event event) {
                return event.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public List<Event> getAllEvents() {
        return dbManager.find(Event.class);
    }

    public void updateEvent(Event updatedEvent) {
        getEventById(updatedEvent.getId()).ifPresent(existingEvent -> {
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setStartDate(updatedEvent.getStartDate());
            existingEvent.setEndDate(updatedEvent.getEndDate());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setReminderEnabled(updatedEvent.isReminderEnabled());
            existingEvent.setTags(updatedEvent.getTags());
            existingEvent.setReminders(updatedEvent.getReminders());
            dbManager.save(existingEvent);
        });
    }

    public void deleteEvent(Event event) {
        dbManager.delete(event);
    }
}
