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

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class AddGradeTest2 {

    @Test
    public void testAddGrade2() {
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

        Student student = addStudent(studentService, "1", "Nume", 935, "a@b.c", "Prof");
        Teme theme = addTheme(themeService, 1, "Desc", 1, 2);
        Map.Entry<String, Integer> gradeId = new AbstractMap.SimpleEntry<String, Integer>("1", 1);
        Nota grade = new Nota(gradeId, student, theme, 5, 1);
        service.add(grade,"Feedback 1");

        student = addStudent(studentService, "2", "Nume", 935, "a@b.c", "Prof");
        theme = addTheme(themeService, 2, "Desc", 1, 2);
        gradeId = new AbstractMap.SimpleEntry<String, Integer>("2", 2);
        grade = new Nota(gradeId, student, theme, 7, 3);
        service.add(grade,"Feedback 2");

        student = addStudent(studentService, "3", "Nume", 935, "a@b.c", "Prof");
        theme = addTheme(themeService, 3, "Desc", 1, 2);
        gradeId = new AbstractMap.SimpleEntry<String, Integer>("3", 3);
        grade = new Nota(gradeId, student, theme, 10, 2);
        service.add(grade,"Feedback 3");

        AtomicInteger counterAfter = new AtomicInteger(0);
        service.all().forEach(i -> {
            counterAfter.addAndGet(1);
        });

        assertEquals(counterAfter.get(), counterBefore.get() + 3);
    }

    Student addStudent(ServiceStudent studentService, String id, String name, int group, String mail, String teacher)
    {
        studentService.add(new Student(id, name, group, mail, teacher));
        return studentService.find(id);
    }

    Teme addTheme(ServiceTeme themeService, int id, String description, int sapt, int deadline)
    {
        themeService.add(new Teme(id, description, sapt, deadline));
        return themeService.find(id);
    }
}
