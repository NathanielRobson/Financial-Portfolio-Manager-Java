import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class AddSharesFrameTest {

    @Test
    public void testDateField() {
        AddSharesFrame addshare = new AddSharesFrame("KL");
        addshare.getdateField();

        boolean test;
        //uses regular expression to see if the pattern matches
        assertEquals(true,Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", "1997-12-12"));

        assertEquals(true,Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", "2001-11-07"));

        assertEquals(false,Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", "1967-12-2"));

        assertEquals(false,Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", "197-121-12"));

        //System.out.println(test);

    }


}