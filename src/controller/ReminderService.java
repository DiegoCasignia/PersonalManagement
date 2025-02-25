package controller;

import com.db4o.query.Predicate;
import model.Reminder;
import java.util.List;
import java.util.Optional;

public class ReminderService {
    private final DatabaseManager dbManager;

    public ReminderService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createReminder(Reminder reminder) {
        dbManager.save(reminder);
    }

    public Optional<Reminder> getReminderById(String id) {
        return dbManager.findByQuery(new Predicate<Reminder>() {
            @Override
            public boolean match(Reminder reminder) {
                return reminder.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public List<Reminder> getAllReminders() {
        return dbManager.find(Reminder.class);
    }

    public void deleteReminder(Reminder reminder) {
        dbManager.delete(reminder);
    }
}
