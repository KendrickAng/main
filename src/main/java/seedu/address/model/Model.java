package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Interviewee;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    // IntervieweeList and InterviewerList (Ken)
    // ========================================================================================


    /**
     * Adds an interviewer to the model's {@code InterviewerList}. Must be unique.
     */
    void addInterviewer(Interviewer interviewer);

    /**
     * Adds an interviewee to the model's {@code IntervieweeList}. Must be unique.
     */
    void addInterviewee(Interviewee interviewee);

    /**
     * Returns the interviewee book.
     */
    ReadAndWriteList<Interviewee> getIntervieweeList();

    /**
     * Returns the interviewer book.
     */
    ReadAndWriteList<Interviewer> getInterviewerList();

    /**
     * Returns an unmodifiable list view of {@code Interviewee} backed by the internal list of {@code IntervieweeList}.
     */
    ObservableList<Interviewee> getFilteredIntervieweeList();

    /**
     * Returns an unmodifiable list view of {@code Interviewer} backed by the internal list of {@code InterviewerList}.
     */
    ObservableList<Interviewer> getFilteredInterviewerList();

    /**
     * Restricts the {@code ObservableList} of interviewee to display only what returns true on Predicate while
     * leaving the original {@code IntervieweeList} unmodified.
     */
    void updateFilteredIntervieweeList(Predicate<Interviewee> predicate);

    /**
     * Restricts the {@code ObservableList} of interviewer to display only what returns true on Predicate while
     * leaving the original {@code InterviewerList} unmodified.
     */
    void updateFilteredInterviewerList(Predicate<Interviewer> predicate);

    /**
     * Returns an Interviewee given a name. The Interviewee must exist in the database, or an exception is thrown.
     */
    Interviewee getInterviewee(String name) throws NoSuchElementException;

    /**
     * Returns an Interviewer given a name. The Interviewer must exist in the database, or an exception is thrown.
     */
    Interviewer getInterviewer(String name) throws NoSuchElementException;

    /**
     * Deletes the given interviewee from the {@code IntervieweeList}. The interviewee must exist in the interviewee book.
     */
    void deleteInterviewee(Interviewee target) throws PersonNotFoundException;

    /**
     * Deletes the given interviewer from the {@code InterviewerList}. The interviewer must exist in the book.
     */
    void deleteInterviewer(Interviewer target) throws PersonNotFoundException;

    // ==================================================================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Replaces schedule data with the data in {@code schedule}.
     */
    void setSchedulesList(List<Schedule> schedulesList);

    /**
     * Sets interviewee's data.
     */
    void setIntervieweesList(List<Interviewee> list);

    /** Returns the schedulesList **/
    List<Schedule> getSchedulesList();

    /**
     * Adds an interviewer to one of the schedules if the interviewer's availability fall within those schedules
     * and returns true. Otherwise, the method will not addEntity the interviewer and return false.
     */
    void addInterviewerToSchedule(Interviewer interviewer);

    /** Returns the intervieweesList **/
    List<Interviewee> getIntervieweesList();

    /**
     * Returns a list of observable list of the schedules.
     */
    List<ObservableList<ObservableList<String>>> getObservableLists();

    /** Returns a list of lists of column titles, each list of column titles belong to a Schedule table*/
    List<List<String>> getTitlesLists();

    /**
     * Emails the given Interviewee.
     * The Interviewee must exist in the database.
     */
    void emailInterviewee(Interviewee interviewee) throws IOException;

    /**
     * Returns the interview slot assigned to the interviewee with the {@code intervieweeName}.
     */
    List<Slot> getInterviewSlots(String intervieweeName);

    /**
     * Returns the date of the schedule in which the interviewer exists in, otherwise return empty string.
     */
    String hasInterviewer(Interviewer interviewer);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target) throws PersonNotFoundException;

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Returns the Person object given the name.
     * The person must exist in the address book.
     * @param name The name of the Person
     * @throws NoSuchElementException If the person does not exist in the address book.
     */
    Person getPerson(String name) throws NoSuchElementException;

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
