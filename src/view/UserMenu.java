package view;

import controller.UserService;
import controller.ContactService;
import controller.TaskService;
import controller.NoteService;
import controller.EventService;
import model.*;

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
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                System.out.println((i + 1) + ". 📝 Nombre: " + contact.getName());
                System.out.println("   📞 Teléfono: " + contact.getPhone());
                System.out.println("   \uD83D\uDCE7 Correo: " + contact.getEmail());
                System.out.println("   📍 Dirección: " + contact.getAddress());
                System.out.println("   📌 Notas: " + contact.getNotes());
                System.out.println("-----------------------------");
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
        userService.addContactToUser(user.getId(), contact);

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
            System.out.println((i + 1) + ". 📝 Título: " + task.getTitle());
            System.out.println("   📌 Descripción: " + task.getDescription());
            System.out.println("   📅 Fecha: " + dateFormat.format(task.getDueDate()));
            System.out.println("   ✅ Estado: " + (task.isCompleted() ? "Completada" : "Pendiente"));
            List<Tag> tags = task.getTags();
            for (int j = 0; j < tags.size(); j++) {
                Tag tag = tags.get(j);
                System.out.println("   \uD83D\uDD34 Etiqueta: " + tag.getName());
            }
            List<Reminder> reminders = task.getReminders();
            for (int j = 0; j < reminders.size(); j++) {
                Reminder reminder = reminders.get(j);
                System.out.println("   ⏰ Recordatorio: " + reminder.getMessage());
                System.out.println("   \uD83D\uDCC5 Fecha de Recordatorio: " + dateFormat.format(reminder.getDateTime()));
                System.out.println("   ✅ Repetir recordatorio: " + (task.isCompleted() ? "Si" : "No"));
            }
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
        String dateString1 = scanner.nextLine();
        System.out.print("Estado (true = completada, false = pendiente): ");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Etiquieta: ");
        String nameTag = scanner.nextLine();
        System.out.print("Color: ");
        String colorTag = scanner.nextLine();
        System.out.print("Fecha de recordatorio (YYYY-MM-DD): ");
        String dateString2 = scanner.nextLine();
        System.out.print("Mensaje de recordatorio: ");
        String messageReminder = scanner.nextLine();
        System.out.print("Repetir recordatorio (true = Si, false = No): ");
        boolean repeatReminder = scanner.nextBoolean();
        scanner.nextLine();

        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            date1 = dateFormat.parse(dateString1);
            date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) { }

        Task task = new Task(title, description, date1, 1,completed);
        Tag tag = new Tag(nameTag, colorTag);
        Reminder reminder = new Reminder(messageReminder, date2, repeatReminder);
        task.addReminder(reminder);
        task.addTag(tag);
        userService.addTaskToUser(user.getId(), task);

        System.out.println("✅ Tarea agregada correctamente.");
    }

    private void viewNotes() {
        System.out.println("\n=== LISTA DE NOTAS ===");
        List<Note> notes = user.getNotes();
        if (notes == null || notes.isEmpty()) {
            System.out.println("⚠️ No tienes notas registradas.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println((i + 1) + ". 📝 Título: " + note.getTitle());
            System.out.println("   📌 Contenido: " + note.getContent());
            System.out.println("   📅 Fecha: " + dateFormat.format(note.getCreationDate()));
            List<Tag> tags = note.getTags();
            for (int j = 0; j < tags.size(); j++) {
                Tag tag = tags.get(j);
                System.out.println("   \uD83D\uDD34 Etiqueta: " + tag.getName());
            }
            System.out.println("-----------------------------");
        }
    }

    private void addNote() {
        System.out.println("\n=== AGREGAR NOTA ===");
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Contenido: ");
        String content = scanner.nextLine();
        System.out.print("Etiquieta: ");
        String nameTag = scanner.nextLine();
        System.out.print("Color: ");
        String colorTag = scanner.nextLine();

        Note note = new Note(title, content);
        Tag tag = new Tag(nameTag, colorTag);
        note.addTag(tag);
        userService.addNoteToUser(user.getId(), note);

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
            System.out.println((i + 1) + ". 📝 Título: " + event.getTitle());
            System.out.println("   📝 Descripción: " + event.getDescription());
            System.out.println("   📅 Fecha Inicio: " + dateFormat.format(event.getStartDate()));
            System.out.println("   📅 Fecha Fin: " + dateFormat.format(event.getEndDate()));
            System.out.println("   📍 Lugar: " + event.getLocation());
            System.out.println("   🔔 Recordatorio: " + (event.isReminderEnabled() ? "Sí" : "No"));
            List<Tag> tags = event.getTags();
            for (int j = 0; j < tags.size(); j++) {
                Tag tag = tags.get(j);
                System.out.println("   \uD83D\uDD34 Etiqueta: " + tag.getName());
            }
            List<Reminder> reminders = event.getReminders();
            for (int j = 0; j < reminders.size(); j++) {
                Reminder reminder = reminders.get(j);
                System.out.println("   ⏰ Recordatorio: " + reminder.getMessage());
                System.out.println("   \uD83D\uDCC5 Fecha de Recordatorio: " + dateFormat.format(reminder.getDateTime()));
                System.out.println("   ✅ Repetir recordatorio: " + (reminder.isRepeat() ? "Si" : "No"));
            }
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
        scanner.nextLine();
        System.out.print("Etiquieta: ");
        String nameTag = scanner.nextLine();
        System.out.print("Color: ");
        String colorTag = scanner.nextLine();
        System.out.print("Fecha de recordatorio (YYYY-MM-DD): ");
        String dateString2 = scanner.nextLine();
        System.out.print("Repetir recordatorio (true = completada, false = pendiente): ");
        boolean repeatReminder = scanner.nextBoolean();
        scanner.nextLine();

        Date startDate = new Date();
        Date endDate = new Date();
        Date date2 = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
            date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) { }

        Event event = new Event(title, description, startDate, endDate, location, completed);
        Tag tag = new Tag(nameTag, colorTag);
        Reminder reminder = new Reminder(title, date2, repeatReminder);
        event.addReminder(reminder);
        event.addTag(tag);
        userService.addEventToUser(user.getId(), event);

        System.out.println("✅ Evento agregado correctamente.");
    }
}
