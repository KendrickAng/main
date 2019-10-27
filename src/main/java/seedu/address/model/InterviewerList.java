package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.collections.ObservableList;

import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Name;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Wraps all interviewee data in the model.
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class InterviewerList implements ReadAndWriteList<Interviewer> {

    private final UniquePersonList<Interviewer> interviewers;

    public InterviewerList() {
        this.interviewers = new UniquePersonList<>();
    }

    public InterviewerList(ReadOnlyList<Interviewer> book) {
        this();
        resetData(book);
    }

    /**
     * Adds the interviewer to the book. The interviewer must be unique.
     * @throws DuplicatePersonException if the interviewer already exists in the book.
     */
    @Override
    public void addEntity(Interviewer interviewer) throws DuplicatePersonException {
        interviewers.add(interviewer);
    }

    @Override
    public Interviewer getEntity(Name name) throws PersonNotFoundException {
        requireNonNull(name);
        Optional<Interviewer> i = interviewers.asUnmodifiableObservableList().stream()
                .filter(interviewer -> interviewer.getName().equals(name))
                .findAny();
        if (!i.isPresent()) {
            throw new PersonNotFoundException();
        }
        return i.get();
    }

    @Override
    public void removeEntity(Interviewer interviewer) throws PersonNotFoundException {
        interviewers.remove(interviewer);
    }

    @Override
    public ObservableList<Interviewer> getObservableList() {
        return interviewers.asUnmodifiableObservableList();
    }

    /**
     * Resets the underlying {@code UniquePersonList<Interviewer>} with that of the {@code book}.
     */
    private void resetData(ReadOnlyList<Interviewer> book) {
        requireNonNull(book);
        this.interviewers.setPersons(book.getObservableList());
    }

    @Override
    public String toString() {
        return interviewers.asUnmodifiableObservableList().size() + " interviewees";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewerList// instanceof handles nulls
                && interviewers.equals(((InterviewerList) other).interviewers));
    }

    @Override
    public int hashCode() {
        return interviewers.hashCode();
    }
}
