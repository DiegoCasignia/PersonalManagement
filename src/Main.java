import view.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginView = new Login();
            loginView.setVisible(true);
        });
    }
}