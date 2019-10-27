package seedu.address.model;

import java.util.NoSuchElementException;

import javafx.collections.ObservableList;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Address book that behaves like a list.
 * @param <T> Interviewee or Interviewer.
 */
public interface ListBasedBook<T extends Person> {
    /**
     * Adds a person to the book. This book will not contain duplicates.
     */
    void add(T person);

    /**
     * Gets the person with specified name.
     * @throws NoSuchElementException if nobody with the name exists.
     */
    T getEntity(Name name) throws PersonNotFoundException;

    /**
     * Removes the person with specified name from the book. The person must exist in the book.
     * @throws PersonNotFoundException if the person does not exist in the book.
     */
    void removeEntity(T person) throws PersonNotFoundException;

    /**
     * Returns an unmodifiable view of the entity list.
     * This list will not contain any duplicate entities.
     */
    ObservableList<T> getObservableList();
}
