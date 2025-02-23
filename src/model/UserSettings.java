package model;

public class UserSettings {
    private boolean darkTheme;
    private String language;
    private boolean notificationsEnabled;

    public UserSettings(boolean darkTheme, String language, boolean notificationsEnabled) {
        this.darkTheme = darkTheme;
        this.language = language;
        this.notificationsEnabled = notificationsEnabled;
    }

    public UserSettings() {
        this.darkTheme = false;
        this.language = "Español";
        this.notificationsEnabled = false;
    }

    // Getters and Setters
    public boolean isDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
}
