package seedu.address.model.person;

import static seedu.address.model.person.EmailType.NUS;
import static seedu.address.model.person.EmailType.PERSONAL;

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
    private final List<Department> departmentChoices; // choice of departments
    private final List<Slot> availableTimeslots; // allocated interview time slots
    private final Emails emails = new Emails(); // personal, NUS emails etc

    /**
     * Every field must be present and not null.
     */
    private Interviewee(Email personalEmail, Email nusEmail, Faculty faculty, Integer yearOfStudy,
                       List<Department> departmentChoices, List<Slot> availableTimeslots,
                       Name name, Phone phone, Address address, Set<Tag> tags) {
        super(name, phone, address, tags);
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.departmentChoices = departmentChoices;
        this.availableTimeslots = availableTimeslots;
        emails.addEmail(PERSONAL, personalEmail);
        emails.addEmail(NUS, nusEmail);
    }

    /**
     * Builder class for Interviewee - allows certain fields to optionally be added.
     */
    public static class IntervieweeBuilder {
        // Required parameters for Person
        private final Name name;
        private final Phone phone;
        private final Address address;
        private final Set<Tag> tags;

        // Optional parameters - initialised to default values
        private Email personalEmail = DefaultValues.DEFAULT_PERSONAL_EMAIL;
        private Email nusEmail = DefaultValues.DEFAULT_NUS_EMAIL;
        private Faculty faculty = DefaultValues.DEFAULT_FACULTY;
        private Integer yearOfStudy = DefaultValues.DEFAULT_YEAR_OF_STUDY;
        private List<Department> departmentChoices = DefaultValues.DEFAULT_DEPARTMENTS;
        private List<Slot> availableTimeslots = DefaultValues.DEFAULT_TIMESLOTS;

        public IntervieweeBuilder(Name name, Phone phone, Address address, Set<Tag> tags) {
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.tags = tags;
        }

        public IntervieweeBuilder personalEmail(Email val) {
            personalEmail = val;
            return this;
        }

        public IntervieweeBuilder nusEmail(Email val) {
            nusEmail = val;
            return this;
        }

        public IntervieweeBuilder faculty(Faculty val) {
            faculty = val;
            return this;
        }

        public IntervieweeBuilder yearOfStudy(Integer val) {
            yearOfStudy = val;
            return this;
        }

        public IntervieweeBuilder departmentChoices(List<Department> val) {
            departmentChoices = val;
            return this;
        }

        public IntervieweeBuilder availableTimeslots(List<Slot> val) {
            availableTimeslots = val;
            return this;
        }

        public Interviewee build() {
            return new Interviewee(personalEmail, nusEmail, faculty, yearOfStudy, departmentChoices,
                    availableTimeslots, name, phone, address, tags);
        }
    }

    // Getters and misc methods
    public Faculty getFaculty() {
        return faculty;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public List<Department> getDepartmentChoices() {
        return departmentChoices;
    }

    public List<Slot> getAvailableTimeslots() {
        return availableTimeslots;
    }

    public Emails getEmails() {
        return emails;
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
                && otherInterviewee.getEmails().equals(getEmails())
                && otherInterviewee.getFaculty().equals(getFaculty())
                && otherInterviewee.getYearOfStudy().equals(getYearOfStudy())
                && otherInterviewee.getDepartmentChoices().equals(getDepartmentChoices())
                && otherInterviewee.getAvailableTimeslots().equals(getAvailableTimeslots());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(emails, faculty, yearOfStudy, departmentChoices, availableTimeslots,
                getName(), getPhone(), getAddress(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Emails: ")
                .append(getEmails())
                .append(" Address: ")
                .append(getAddress())
                .append(" Faculty: ")
                .append(getFaculty())
                .append(" Year of study: ")
                .append(getYearOfStudy())
                .append(" Choice of departments: ")
                .append(getDepartmentChoices())
                .append(" Time slots: ")
                .append(getAvailableTimeslots())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
