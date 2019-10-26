package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;

/**
 * Deletes a person identified using it's name and role from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by his/her name and role.\n"
            + "Parameters: NAME (case-sensitive), ROLE (interviewee/interviewer)\n"
            + "Example: " + COMMAND_WORD + " John Doe" + "r/interviewee";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;
    // TODO: make use of targetRole after implementing Model changes.
    private final Role targetRole;

    public DeleteCommand(Name targetName, Role targetRole) {
        this.targetName = targetName;
        this.targetRole = targetRole;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToDelete;
        try {
            personToDelete = model.getPerson(targetName.fullName);
        } catch (NoSuchElementException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME + ": " + targetName);
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetName.equals(((DeleteCommand) other).targetName) // state check
                && targetName.equals(((DeleteCommand) other).targetName));
    }
}
