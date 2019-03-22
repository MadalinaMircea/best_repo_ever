import Domain.Student;
import Repository.StudentRepo;
import Service.ServiceStudent;
import Validator.StudentValidator;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddStudentTest {

    @Test
    public void testAddStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"C:\\Temp\\studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        service.del("123");

        assertEquals(service.find("123"), null);

        service.add(new Student("123", "Nume", 935, "a@b.c", "Prof"));

        assertNotEquals(service.find("123"), null);

        service.del("123");
    }
}
