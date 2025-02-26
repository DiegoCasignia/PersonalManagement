package view;

import controller.UserService;
import controller.ContactService;
import controller.TaskService;
import controller.NoteService;
import controller.EventService;
import model.User;
import model.Contact;
import model.Task;
import model.Note;
import model.Event;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.text.ParseException;

public class UserMenu {
    private final User user;
    private final UserService userService;
    private final ContactService contactService;
    private final TaskService taskService;
    private final NoteService noteService;
    private final EventService eventService;
    private final Scanner scanner;

    public UserMenu(User user) {
        this.user = user;
        this.userService = new UserService();
        this.contactService = new ContactService();
        this.taskService = new TaskService();
        this.noteService = new NoteService();
        this.eventService = new EventService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n==== MENÚ DE USUARIO ====");
            System.out.println("1. Ver contactos");
            System.out.println("2. Agregar contacto");
            System.out.println("3. Ver tareas");
            System.out.println("4. Agregar tarea");
            System.out.println("5. Ver notas");
            System.out.println("6. Agregar nota");
            System.out.println("7. Ver eventos");
            System.out.println("8. Agregar evento");
            System.out.println("9. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (option) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    addTask();
                    break;
                case 5:
                    viewNotes();
                    break;
                case 6:
                    addNote();
                    break;
                case 7:
                    viewEvents();
                    break;
                case 8:
                    addEvent();
                    break;
                case 9:
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void viewContacts() {
        System.out.println("\n=== LISTA DE CONTACTOS ===");
        List<Contact> contacts = user.getContacts();
        if (contacts == null || contacts.isEmpty()) {
            System.out.println("⚠️ No tienes contactos registrados.");
        } else {
            System.out.println("\n📞 Lista de Contactos:");
            for (Contact contact : contacts) {
                System.out.println("- " + contact.getName() + " | " + contact.getPhone() + " | " + contact.getEmail());
            }
        }
    }

    private void addContact() {
        System.out.println("\n=== AGREGAR CONTACTO ===");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Correo: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String address = scanner.nextLine();
        System.out.print("Notas: ");
        String notes = scanner.nextLine();

        Contact contact = new Contact(name, phone, email, address, notes);
        System.out.println(user.getName());
        user.addContact(contact);
        contactService.createContact(contact);
        userService.updateUser(user);

        System.out.println("✅ Contacto agregado correctamente.");
    }

    private void viewTasks() {
        System.out.println("\n=== LISTA DE TAREAS ===");

        List<Task> tasks = user.getTasks();

        if (tasks == null || tasks.isEmpty()) {
            System.out.println("⚠️ No tienes tareas registradas.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getTitle());
            System.out.println("   📌 Descripción: " + task.getDescription());
            System.out.println("   📅 Fecha: " + dateFormat.format(task.getDueDate()));
            System.out.println("   ✅ Estado: " + (task.isCompleted() ? "Completada" : "Pendiente"));
            System.out.println("-----------------------------");
        }
    }

    private void addTask() {
        System.out.println("\n=== AGREGAR TAREA ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        System.out.print("Estado (true = completada, false = pendiente): ");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) { }

        Task task = new Task(title, description, date, 1,completed);
        user.addTask(task);
        taskService.createTask(task);
        userService.updateUser(user);

        System.out.println("✅ Tarea agregada correctamente.");
    }

    private void viewNotes() {
        System.out.println("\n=== LISTA DE NOTAS ===");
        List<Note> notes = user.getNotes();
        if (notes == null || notes.isEmpty()) {
            System.out.println("⚠️ No tienes notas registradas.");
        } else {
            System.out.println("\n📝 Lista de Notas:");
            for (Note note : notes) {
                System.out.println("- " + note.getTitle() + ": " + note.getContent());
            }
        }
    }

    private void addNote() {
        System.out.println("\n=== AGREGAR NOTA ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Contenido: ");
        String content = scanner.nextLine();

        Note note = new Note(title, content);
        user.addNote(note);
        noteService.createNote(note);
        userService.updateUser(user);

        System.out.println("✅ Nota agregada correctamente.");
    }

    private void viewEvents() {
        System.out.println("\n=== LISTA DE EVENTOS ===");

        List<Event> events = user.getEvents();

        if (events == null || events.isEmpty()) {
            System.out.println("⚠️ No tienes eventos registrados.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            System.out.println((i + 1) + ". " + event.getTitle());
            System.out.println("   📝 Descripción: " + event.getDescription());
            System.out.println("   📅 Fecha Inicio: " + dateFormat.format(event.getStartDate()));
            System.out.println("   📅 Fecha Fin: " + dateFormat.format(event.getEndDate()));
            System.out.println("   📍 Lugar: " + event.getLocation());
            System.out.println("   🔔 Recordatorio: " + (event.isReminderEnabled() ? "Sí" : "No"));
            System.out.println("-----------------------------");
        }
    }

    private void addEvent() {
        System.out.println("\n=== AGREGAR EVENTO ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Fecha Inicio (YYYY-MM-DD): ");
        String startDateString = scanner.nextLine();
        System.out.print("Fecha Fin (YYYY-MM-DD): ");
        String endDateString = scanner.nextLine();
        System.out.print("Lugar: ");
        String location = scanner.nextLine();
        System.out.print("Recordatorio (true = habilitar, false = deshabilitar): ");
        boolean completed = scanner.nextBoolean();

        Date startDate = new Date();
        Date endDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (ParseException e) { }

        Event event = new Event(title, description, startDate, endDate, location, completed);
        user.addEvent(event);
        eventService.createEvent(event);
        userService.updateUser(user);

        System.out.println("✅ Evento agregado correctamente.");
    }
}
