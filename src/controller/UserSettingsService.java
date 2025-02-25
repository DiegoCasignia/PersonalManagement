package controller;

import model.UserSettings;
import java.util.List;

public class UserSettingsService {
    private final DatabaseManager dbManager;

    public UserSettingsService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void saveUserSettings(UserSettings settings) {
        dbManager.save(settings);
    }

    public List<UserSettings> getAllUserSettings() {
        return dbManager.find(UserSettings.class);
    }

    public void deleteUserSettings(UserSettings settings) {
        dbManager.delete(settings);
    }
}
