package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditIntervieweeCommand.EditIntervieweeDescriptor;
import seedu.address.logic.commands.EditInterviewerCommand.EditInterviewerDescriptor;
import seedu.address.logic.graph.IntervieweeVertex;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailType;
import seedu.address.model.person.Emails;
import seedu.address.model.person.Interviewee;
import seedu.address.model.person.Interviewer;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the interviewee descriptor with details from the Interviewee {@code i}.
     */
    public static EditIntervieweeDescriptor getDescriptorFromInterviewee(Interviewee i) {
        EditIntervieweeDescriptor e = new EditIntervieweeDescriptor();
        e.setName(i.getName());
        e.setPhone(i.getPhone());
        e.setTags(i.getTags());
        e.setFaculty(i.getFaculty());
        e.setYearOfStudy(i.getYearOfStudy());
        e.setDepartmentChoices(i.getDepartmentChoices());
        e.setAvailableTimeslots(i.getAvailableTimeslots());
        e.setEmails(i.getEmails());
        return e;
    }

    public static EditInterviewerDescriptor getDescriptorFromInterviewer(Interviewer i) {
        EditInterviewerDescriptor e = new EditInterviewerDescriptor();
        e.setName(i.getName());
        e.setPhone(i.getPhone());
        e.setDepartment(i.getDepartment());
        e.setAvailabilities(i.getAvailabilities());
        e.setEmail(i.getEmail());
        return e;
    }

    public static Emails deepCopyEmails(Emails emails) {
        HashMap<EmailType, List<Email>> map = emails.getAllEmails();
        HashMap<EmailType, List<Email>> deepCopy = new HashMap<>();
        try {
            for (Map.Entry<EmailType, List<Email>> entry: map.entrySet()) {
                EmailType type = entry.getKey();
                List<Email> newList = new ArrayList<>();
                for (Email e: entry.getValue()) {
                    newList.add(ParserUtil.parseEmail(e.value));
                }
                deepCopy.put(type, newList);
            }
        } catch (ParseException e) {
            throw new AssertionError(Messages.MESSAGE_CRITICAL_ERROR);
        }
        return new Emails(deepCopy);
    }

    /**
     * Returns the given two dimensional array of strings as a two dimensional LinkedList of strings.
     */
    public static LinkedList<LinkedList<String>> toTwoDimensionalLinkedList(String[][] table) {
        LinkedList<LinkedList<String>> tableCopy = new LinkedList<>();
        for (String[] row : table) {
            LinkedList<String> rowCopy = new LinkedList<>(Arrays.asList(row));
            tableCopy.add(rowCopy);
        }
        return tableCopy;
    }

    /**
     * Fills up each item in the list given with a sublist.
     */
    public static void fillWithSubLists(List<List<IntervieweeVertex>> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<>());
        }
    }
}
