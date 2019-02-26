/*
If a user bookmarks the page and the ID of an entity has been removed
 */
package sg.thecodetasticfour.superherosightingsgroup.service;

/**
 *
 * @author vishnukdawah
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
