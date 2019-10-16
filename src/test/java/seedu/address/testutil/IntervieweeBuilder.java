package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Department;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Interviewee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.util.SampleDataUtil;

public class IntervieweeBuilder extends PersonBuilder {

    public static final String DEFAULT_FACULTY = "School of Computing";
    public static final String DEFAULT_YEAR_OF_STUDY = "2019";
    public static final String DEFAULT_DEPARTMENT = "Marketing";
    public static final String DEFAULT_SLOT_DATE = "16/10/2019";
    public static final String DEFAULT_SLOT_START = "0000";
    public static final String DEFAULT_SLOT_END = "2359";

    private Faculty faculty;
    private Integer yearOfStudy;
    private List<Department> departmentChoices;
    private List<Slot> allocatedTimeslots;

    public IntervieweeBuilder(Person p) {
        super(p);
        this.faculty = new Faculty(DEFAULT_FACULTY);
        this.yearOfStudy = Integer.parseInt(DEFAULT_YEAR_OF_STUDY);
        this.departmentChoices = new ArrayList<>();
        departmentChoices.add(new Department(DEFAULT_DEPARTMENT));
        this.allocatedTimeslots = new ArrayList<>();
        allocatedTimeslots.add(new Slot(DEFAULT_SLOT_DATE, DEFAULT_SLOT_START, DEFAULT_SLOT_END));
    }

    /**
     * Initializes the IntervieweeBuilder with the data of {@code toCopy}.
     */
    public IntervieweeBuilder(Interviewee toCopy) {
        faculty = toCopy.getFaculty();
        yearOfStudy = toCopy.getYearOfStudy();
        departmentChoices = toCopy.getDepartmentChoices();
        allocatedTimeslots = toCopy.getAvailableTimeslots();
    }

    public IntervieweeBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    public IntervieweeBuilder withYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = Integer.parseInt(yearOfStudy);
        return this;
    }

    public IntervieweeBuilder withDepartmentChoices(String... departments) {
        this.departmentChoices = SampleDataUtil.getDepartmentList(departments);
        return this;
    }

    public IntervieweeBuilder withTimeslots(List<String> dates, List<String> starts, List<String> ends) {
        this.allocatedTimeslots = SampleDataUtil.getTimeslotList(dates, starts, ends);
        return this;
    }

    public Interviewee build() {
        return new Interviewee.IntervieweeBuilder(getName(), getPhone(), getAddress(), getTags())
                    .faculty(faculty)
                    .yearOfStudy(yearOfStudy)
                    .departmentChoices(departmentChoices)
                    .availableTimeslots(allocatedTimeslots)
                    .build();
    }

}
