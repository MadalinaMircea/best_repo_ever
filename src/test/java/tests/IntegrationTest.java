package tests;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTest {
    StudentRepo studentRepo;
    ServiceStudent serviceStudent;
    TemeRepo assignmentRepo;
    ServiceTeme serviceAssignment;
    NoteRepo gradeRepo;
    ServiceNote serviceGrade;

    @Before
    public void setup()
    {
        studentRepo = new StudentRepo(new StudentValidator(), "C:\\Temp\\studenti.xml");
        serviceStudent = new ServiceStudent(studentRepo);
        assignmentRepo=new TemeRepo(new TemeValidator(),"C:\\Temp\\teme.xml");
        serviceAssignment=new ServiceTeme(assignmentRepo);
        gradeRepo=new NoteRepo(new NotaValidator());
        serviceGrade=new ServiceNote(gradeRepo);
    }

    @Test
    public void integrationTest() {
        addStudentTest();
        addAssignmentTest();
        addGradeTest();
    }

    @Test
    public void addStudentTest() {
        serviceStudent.del("123");

        assertEquals(serviceStudent.find("123"), null);

        serviceStudent.add(new Student("123", "Nume", 935, "a@b.c", "Prof"));

        assertNotEquals(serviceStudent.find("123"), null);
    }

    @Test
    public void addAssignmentTest() {
        serviceAssignment.del(1);

        int previousSize = assignmentRepo.size();
        serviceAssignment.add(new Teme(1, "Desc", 1, 2));
        Assert.assertTrue(assignmentRepo.size()==previousSize + 1);
    }

    @Test
    public void addGradeTest() {
        int previousSize = gradeRepo.size();
        Student student = serviceStudent.find("123");
        Teme assignment = serviceAssignment.find(1);
        Map.Entry<String, Integer> id = new AbstractMap.SimpleEntry<String, Integer>(student.getID(), assignment.getID());
        Nota nota = new Nota(id, student, assignment, 10, 2);
        serviceGrade.add(nota, "ok");
        Assert.assertTrue(gradeRepo.size()==previousSize + 1);
    }
}
