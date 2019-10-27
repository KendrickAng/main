package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertModelHasInterviewee;
import static seedu.address.logic.commands.CommandTestUtil.showIntervieweeWithName;
import static seedu.address.testutil.TypicalPersons.ALICE_INTERVIEWEE;
import static seedu.address.testutil.TypicalPersons.BENSON_INTERVIEWEE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalIntervieweeBook;
import static seedu.address.testutil.TypicalPersons.getTypicalInterviewerBook;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.IntervieweeBook;
import seedu.address.model.InterviewerBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Interviewee;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    @Test
    public void execute_validIntervieweeUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkedList<>(),
                getTypicalIntervieweeBook(), new InterviewerBook());
        Interviewee alice = TypicalPersons.ALICE_INTERVIEWEE;
        Role role = new Role("interviewee");

        Interviewee intervieweeToDel = model.getInterviewee(alice.getName().fullName);
        Person personToDel = model.getPerson(alice.getName().fullName);

        DeleteCommand deleteCommand = new DeleteCommand(alice.getName(), role);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, intervieweeToDel);

        // create duplicate and remove interviewee
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkedList<>(),
                model.getIntervieweeBook(), model.getInterviewerBook());

        expectedModel.deleteInterviewee(intervieweeToDel);
        expectedModel.deletePerson(personToDel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInterviewerUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkedList<>(),
                new IntervieweeBook(), getTypicalInterviewerBook());
        Interviewer benson = TypicalPersons.BENSON_INTERVIEWER;
        Role role = new Role("interviewer");

        Interviewer interviewerToDel = model.getInterviewer(benson.getName().fullName);
        Person personToDel = model.getPerson(benson.getName().fullName);

        DeleteCommand deleteCommand = new DeleteCommand(benson.getName(), role);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, interviewerToDel);

        // create duplicate and remove interviewee
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkedList<>(),
                model.getIntervieweeBook(), model.getInterviewerBook());

        expectedModel.deleteInterviewer(interviewerToDel);
        expectedModel.deletePerson(personToDel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkedList<>(),
                getTypicalIntervieweeBook(), new InterviewerBook());

        DeleteCommand deleteCommand = new DeleteCommand(new Name("This name doesnt exist in IntervieweeBook"),
                new Role("interviewee"));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);
        // pre-processing
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkedList<>(),
                getTypicalIntervieweeBook(), new InterviewerBook());
        Interviewee toDelete = ALICE_INTERVIEWEE;
        Person personToDelete = model.getPerson(ALICE_INTERVIEWEE.getName().fullName);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, toDelete);

        assertModelHasInterviewee(model, toDelete);

        showIntervieweeWithName(model, ALICE_INTERVIEWEE.getName());

        DeleteCommand deleteCommand = new DeleteCommand(toDelete.getName(), new Role("interviewee"));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new LinkedList<>(),
                model.getIntervieweeBook(), model.getInterviewerBook());
        expectedModel.deleteInterviewee(toDelete);
        expectedModel.deletePerson(personToDelete);

        showNoInterviewee(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new LinkedList<>(),
                getTypicalIntervieweeBook(), new InterviewerBook());

        showIntervieweeWithName(model, ALICE_INTERVIEWEE.getName());

        Name invalidName = new Name("This name doesnt exist in the interviewee book");

        DeleteCommand deleteCommand = new DeleteCommand(invalidName, new Role("interviewee"));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_NAME);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ALICE_INTERVIEWEE.getName(), new Role("interviewee"));
        DeleteCommand deleteSecondCommand = new DeleteCommand(BENSON_INTERVIEWEE.getName(), new Role("interviewee"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ALICE_INTERVIEWEE.getName(), new Role("interviewee"));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered interviewee list to show no one.
     */
    private void showNoInterviewee(Model model) {
        model.updateFilteredIntervieweeList(p -> false);

        assertTrue(model.getFilteredIntervieweeList().isEmpty());
    }
}
