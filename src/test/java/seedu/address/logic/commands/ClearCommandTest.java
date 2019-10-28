package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalIntervieweeBook;
import static seedu.address.testutil.TypicalPersons.getTypicalInterviewerBook;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalIntervieweeBook(), getTypicalInterviewerBook(),
                new UserPrefs(), new LinkedList<>());
        Model expectedModel = new ModelManager(getTypicalIntervieweeBook(), getTypicalInterviewerBook(),
                new UserPrefs(), new LinkedList<>());
        expectedModel.setIntervieweeList(List.of());
        expectedModel.setInterviewerList(List.of());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
