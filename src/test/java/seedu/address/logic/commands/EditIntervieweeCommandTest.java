package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.EditIntervieweeCommand.EditIntervieweeDescriptor;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE_INTERVIEWEE;
import static seedu.address.testutil.TypicalPersons.BOB_INTERVIEWEE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.testutil.TestUtil;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests.
 */
class EditIntervieweeCommandTest {

    // ============================================== UNIT TESTS =================================================

    @Test
    public void constructor_nullFieldsInput_failure() {
        assertThrows(NullPointerException.class,
                () -> new EditIntervieweeCommand(new Name("Alice"), null));
        assertThrows(NullPointerException.class,
                () -> new EditIntervieweeCommand(null, new EditIntervieweeCommand.EditIntervieweeDescriptor()));
        assertThrows(NullPointerException.class,
                () -> new EditIntervieweeCommand(null, null));
    }

    @Test
    public void equals() {

        final EditIntervieweeDescriptor aliceDescriptor = TestUtil.getDescriptorFromInterviewee(ALICE_INTERVIEWEE);
        final EditIntervieweeCommand standardCommand =
                new EditIntervieweeCommand(ALICE_INTERVIEWEE.getName(), aliceDescriptor);

        EditIntervieweeDescriptor copyDescriptor = new EditIntervieweeDescriptor(aliceDescriptor);
        EditIntervieweeCommand commandWithSameValues =
                new EditIntervieweeCommand(ALICE_INTERVIEWEE.getName(), copyDescriptor);
        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        assertFalse(standardCommand.equals(new EditIntervieweeCommand(BOB_INTERVIEWEE.getName(), aliceDescriptor)));

        // different descriptor -> returns false
        final EditIntervieweeDescriptor bobDescriptor = TestUtil.getDescriptorFromInterviewee(BOB_INTERVIEWEE);
        assertFalse(standardCommand.equals(new EditIntervieweeCommand(ALICE_INTERVIEWEE.getName(), bobDescriptor)));
    }
}