package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewer in the scheduler.
 */
public class Interviewer extends Person {

    private final List<Schedule> schedules;
    private final Department department;

    /**
     * Every field must be present and not null.
     */
    private Interviewer(List<Schedule> schedules, Department department,
                        Name name, Phone phone, Address address, Set<Tag> tags) {
        super(name, phone, address, tags);
        this.schedules = schedules;
        this.department = department;
    }

    public static class InterviewerBuilder {
        // Required parameters for Person
        private final Name name;
        private final Phone phone;
        private final Address address;
        private final Set<Tag> tags;

        // Optional parameters - initialised to default values
        private List<Schedule> schedules = DefaultValues.DEFAULT_SCHEDULES;
        private Department department = DefaultValues.DEFAULT_DEPARTMENT;

        public InterviewerBuilder(Name name, Phone phone, Address address, Set<Tag> tags) {
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.tags = tags;
        }

        public InterviewerBuilder schedules(List<Schedule> val) {
            schedules = val;
            return this;
        }

        public InterviewerBuilder department(Department val) {
            department = val;
            return this;
        }

        public Interviewer build() {
            return new Interviewer(schedules, department, name, phone, address, tags);
        }
    }

    // Getters and misc methods
    public Department getDepartment() {
        return department;
    }

    public List<Schedule> getSchedules() {
        return schedules;
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

        if (!(other instanceof Interviewer)) {
            return false;
        }

        Interviewer otherInterviewer = (Interviewer) other;
        return super.equals(other)
                && otherInterviewer.getDepartment().equals(getDepartment())
                && otherInterviewer.getSchedules().equals(getSchedules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(department, schedules,
                getName(), getPhone(), getAddress(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Role ")
                .append(getClass().getSimpleName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Address: ")
                .append(getAddress())
                .append(" Department ")
                .append(getDepartment())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
