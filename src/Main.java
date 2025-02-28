import controller.UserService;
import model.User;
import model.UserSettings;
import view.UserMenu;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static final UserService userService = new UserService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Ver Usuarios Registrados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    List<User> users = userService.getAllUsers();
                    System.out.println("\n=== USUARIOS REGISTRADOS ===");
                    for (User user : users) {
                        System.out.println("Nombre: " + user.getName() + ", Email: " + user.getEmail());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void login() {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Correo: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            System.out.println("✅ Bienvenido, " + userOptional.get().getName() + "!");
            UserMenu userMenu = new UserMenu(userOptional.get());
            userMenu.showMenu();
        } else {
            System.out.println("❌ Credenciales incorrectas. Intente nuevamente.");
        }
    }

    private static void register() {
        System.out.println("\n=== REGISTRO ===");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Correo: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        // Configuración por defecto
        UserSettings settings = new UserSettings(false, "Español", true);
        User newUser = new User(name, email, password, settings);

        try {
            userService.createUser(newUser);
            System.out.println("✅ Usuario registrado con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
