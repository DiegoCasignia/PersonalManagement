package controller;

import com.db4o.*;
import com.db4o.query.Predicate;
import java.io.File;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance;
    private ObjectContainer db;
    private static final String DB_FILE = "database.db4o";

    private DatabaseManager() {
        openDatabase();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void openDatabase() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }

    public void save(Object obj) {
        try {
            db.store(obj);
            db.commit();
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Object obj) {
        try {
            db.delete(obj);
            db.commit();
        } catch (Exception e) {
            db.rollback();
            e.printStackTrace();
        }
    }

    public <T> List<T> find(Class<T> clazz) {
        return db.query(clazz);
    }

    public <T> List<T> findByQuery(Predicate<T> predicate) {
        return db.query(predicate);
    }

    public void backupDatabase() {
        try {
            File backup = new File("backup_" + System.currentTimeMillis() + ".db4o");
            db.ext().backup(backup.getAbsolutePath());
            System.out.println("Backup created: " + backup.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

