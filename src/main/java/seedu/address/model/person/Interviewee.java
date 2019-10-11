package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewee in the scheduler.
 */
public class Interviewee extends Person {

    private final Faculty faculty;
    private final Integer yearOfStudy;
    private final List<Department> departments; // choice of departments
    private final List<Slot> timeslots; // allocated interview time slots

    /**
     * Every field must be present and not null.
     */
    public Interviewee(Faculty faculty, Integer yearOfStudy, List<Department> departments, List<Slot> timeslots,
                       Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.departments = departments;
        this.timeslots = timeslots;
    }

    // Getters and misc methods
    public Faculty getFaculty() {
        return faculty;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Slot> getTimeslots() {
        return timeslots;
    }

    /**
     * Returns true if both interviewees have the same identity and data fields.
     * This defines a stronger notion of equality between two interviewees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interviewee)) {
            return false;
        }

        Interviewee otherInterviewee = (Interviewee) other;
        return super.equals(other)
                && otherInterviewee.getFaculty().equals(getFaculty())
                && otherInterviewee.getYearOfStudy().equals(getYearOfStudy())
                && otherInterviewee.getDepartments().equals(getDepartments())
                && otherInterviewee.getTimeslots().equals(getTimeslots());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(faculty, yearOfStudy, departments, timeslots,
                getName(), getPhone(), getAddress(), getEmail(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Faculty: ")
                .append(getFaculty())
                .append(" Year of study: ")
                .append(getYearOfStudy())
                .append(" Choice of departments: ")
                .append(getDepartments())
                .append(" Time slots: ")
                .append(getTimeslots())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
