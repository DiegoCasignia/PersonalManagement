package controller;

import model.Note;
import com.db4o.query.Predicate;
import java.util.List;
import java.util.Optional;

public class NoteService {
    private final DatabaseManager dbManager;

    public NoteService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createNote(Note note) {
        dbManager.save(note);
    }

    public Optional<Note> getNoteById(String id) {
        return dbManager.findByQuery(new Predicate<Note>() {
            @Override
            public boolean match(Note note) {
                return note.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public List<Note> getAllNotes() {
        return dbManager.find(Note.class);
    }

    public void deleteNote(Note note) {
        dbManager.delete(note);
    }
}
