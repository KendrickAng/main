package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ANSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class JsonAdaptedIntervieweeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = ANSON.getName().toString();
    private static final String VALID_PHONE = ANSON.getPhone().toString();
    private static final String VALID_ADDRESS = ANSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ANSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validIntervieweeDetails_returnsInterviewee() throws Exception {
        JsonAdaptedInterviewee interviewee = new JsonAdaptedInterviewee(ANSON);
        assertEquals(ANSON, interviewee.toModelType());
    }
}