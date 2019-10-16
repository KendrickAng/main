package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an interview timeslot an {@code Interviewee} is allocated.
 */
public class Slot {

    public static final String MESSAGE_CONSTRAINTS = "Placeholder";
    public static final String SLOT_VALIDATION_REGEX = "Placeholder";
    public static final String DATE_VALIDATION_REGEX = "Placeholder";
    public static final String STRING_FORMAT = "%s %s - %s";
    // TODO: Abstract out to DateTime class
    // The plan is for users to input DD/MM/YY HH:MM for both start and end
    public final String date;
    public final String start;
    public final String end;

    public Slot(String date, String start, String end) {
        requireAllNonNull(date, start, end);
        // TODO: Argument checking
        // checkArgument(isValidSlot(start), MESSAGE_CONSTRAINTS);
        // checkArgument(isValidSlot(end), MESSAGE_CONSTRAINTS);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns true if the given slot timing is in valid format.
     */
    public static boolean isValidSlot(String test) {
        return test.matches(SLOT_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given date is in valid format.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, start, end);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Slot // instanceof handles nulls
                && date.equals(((Slot) other).date)
                && start.equals(((Slot) other).start)
                && end.equals(((Slot) other).end)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start, end);
    }

}
