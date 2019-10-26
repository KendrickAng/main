package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.person.Person;

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
     * Returns an unmodifiable view of the entity list.
     * This list will not contain any duplicate entities.
     */
    ObservableList<T> getObservableList();
}
