package controller;

import model.Contact;
import com.db4o.query.Predicate;
import java.util.List;
import java.util.Optional;

public class ContactService {
    private final DatabaseManager dbManager;

    public ContactService() {
        this.dbManager = DatabaseManager.getInstance();
    }

    public void createContact(Contact contact) {
        dbManager.save(contact);
    }

    public Optional<Contact> getContactById(String id) {
        return dbManager.findByQuery(new Predicate<Contact>() {
            @Override
            public boolean match(Contact contact) {
                return contact.getId().equals(id);
            }
        }).stream().findFirst();
    }

    public List<Contact> getAllContacts() {
        return dbManager.find(Contact.class);
    }

    public void updateContact(Contact updatedContact) {
        deleteContact(updatedContact);
        createContact(updatedContact);
    }

    public void deleteContact(Contact contact) {
        dbManager.delete(contact);
    }
}
