package controller;

import com.db4o.query.Predicate;
import model.Task;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final DatabaseManager dbManager;

    public TaskService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createTask(Task task) {
        dbManager.save(task);
    }

    public Optional<Task> getTaskById(String id) {
        return dbManager.findByQuery(new Predicate<Task>() {
            @Override
            public boolean match(Task task) {
                return task.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public List<Task> getTasksByPriority(int priority) {
        return dbManager.findByQuery(new Predicate<Task>() {
            @Override
            public boolean match(Task task) {
                return task.getPriority() == priority;
            }
        });
    }

    public void markTaskAsCompleted(String id) {
        getTaskById(id).ifPresent(task -> {
            task.setCompleted(true);
            dbManager.save(task);
        });
    }

    public void deleteTask(Task task) {
        dbManager.delete(task);
    }
}

