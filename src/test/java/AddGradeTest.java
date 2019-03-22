import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemeValidator;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AddGradeTest {

    @Test
    public void testAddGrade() {
        NoteRepo repo = new NoteRepo(new NotaValidator());
        ServiceNote service = new ServiceNote(repo);

        StudentRepo studentRepo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent studentService = new ServiceStudent(studentRepo);

        TemeRepo themeRepo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme themeService = new ServiceTeme(themeRepo);

        AtomicInteger counterBefore = new AtomicInteger(0);
        service.all().forEach(i -> {
            counterBefore.addAndGet(1);
        });

        studentService.add(new Student("1", "Nume", 935, "a@b.c", "Prof"));
        Student student = studentService.find("1");

        themeService.add(new Teme(1, "Desc", 1, 2));
        Teme theme = themeService.find(1);

        Map.Entry<String, Integer> gradeId = new AbstractMap.SimpleEntry<String, Integer>("1", 1);

        Nota grade = new Nota(gradeId, student, theme, 10, 1);

        service.add(grade,"fb means Feedback apparently");

        AtomicInteger counterAfter = new AtomicInteger(0);
        service.all().forEach(i -> {
            counterAfter.addAndGet(1);
        });

        assertEquals(counterAfter.get(), counterBefore.get() + 1);
    }
}
