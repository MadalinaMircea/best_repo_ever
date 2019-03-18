import org.junit.Test;
import static org.junit.Assert.*;

public class AddStudentTest {

    @Test
    public void testConcatenate() {
        AddStudentTest myUnit = new AddStudentTest();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    private String concatenate(String s1, String s2){
        return s1+s2;
    }
}
