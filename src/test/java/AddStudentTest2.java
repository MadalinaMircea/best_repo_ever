import Domain.Student;
import Repository.StudentRepo;
import Service.ServiceStudent;
import Validator.StudentValidator;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AddStudentTest2 {

    @Test
    public void testAddStudent2() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        service.del("555");

        AtomicInteger counterBefore = new AtomicInteger(0);
        service.all().forEach(i -> {
            counterBefore.addAndGet(1);
        });

        assertEquals(service.find("555"), null);

        service.add(new Student("555", "Nume", 935, "a@b.c", "Prof"));
        assertNotEquals(service.find("555"), null);

        AtomicInteger counterAfter = new AtomicInteger(0);
        service.all().forEach(i -> {
            counterAfter.addAndGet(1);
        });

        assertEquals(counterAfter.get(), counterBefore.get() + 1);
    }
}
