package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.person.Interviewer;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Wraps all interviewee data in the model.
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class InterviewerBook implements ListBasedBook<Interviewer> {

    private final UniquePersonList<Interviewer> interviewers;

    public InterviewerBook() {
        this.interviewers = new UniquePersonList<>();
    }

    /**
     * Adds the interviewer to the book. The interviewer must be unique.
     * @throws DuplicatePersonException if the interviewer already exists in the book.
     */
    @Override
    public void add(Interviewer interviewer) throws DuplicatePersonException {
        interviewers.add(interviewer);
    }

    @Override
    public ObservableList<Interviewer> getObservableList() {
        return interviewers.asUnmodifiableObservableList();
    }
}
