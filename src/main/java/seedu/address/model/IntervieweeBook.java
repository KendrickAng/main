package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.person.Interviewee;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

public class IntervieweeBook implements ListBasedBook<Interviewee> {

    private final UniquePersonList<Interviewee> interviewees;

    public IntervieweeBook() {
        this.interviewees = new UniquePersonList<>();
    }

    /**
     * Adds the interviewee to the book. The interviewee must be unique.
     * @throws DuplicatePersonException if the interviewee already exists in the book.
     */
    @Override
    public void add(Interviewee interviewee) throws DuplicatePersonException {
        interviewees.add(interviewee);
    }

    @Override
    public ObservableList<Interviewee> getObservableList() {
        return interviewees.asUnmodifiableObservableList();
    }
}
