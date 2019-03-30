package tests;

import Domain.Student;
import Repository.StudentRepo;
import Service.ServiceStudent;
import Validator.StudentValidator;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddStudentTest {

    @Test
    public void testAddStudentOk() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        service.del("123");

        assertEquals(service.find("123"), null);

        service.add(new Student("123", "Nume", 935, "a@b.c", "Prof"));

        assertNotEquals(service.find("123"), null);

        service.del("123");
    }

    @Test
    public void testAddSameStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        service.add(new Student("1234", "Nume", 935, "a@b.c", "Prof"));

        Assert.assertTrue(repo.size()==currentStud+1);
        try{
            service.add(new Student("1234", "Nume", 935, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            Assert.fail("Student can t be added");
        }
        Assert.assertTrue(repo.size()==currentStud+1);


        service.del("1234");
    }

    @Test
    public void testAddNullIdStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("", "Nume", 935, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("ID invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);


        service.del("1234");
    }

    @Test
    public void testAddStringIdStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("12asd", "Nume", 935, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("ID invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);


        service.del("1234");
    }

    @Test
    public void testAddInvalidGroupStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("12asd", "Nume", 12, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Grupa invalida"));
        }
        Assert.assertTrue(repo.size()==currentStud);


        service.del("1234");
    }

    @Test
    public void testAddInvalidNameStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("1234", "Nume12", 123, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Nume invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);

        service.del("1234");
    }

    @Test
    public void testAddInvalidEmailStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("1234", "Nume", 123, "ab.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Email invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);

        service.del("1234");
    }

    @Test
    public void testAddInvalidEmail2Student() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("1234", "Nume", 123, "abc@yahoo,com", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Email invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);

        service.del("1234");
    }

    @Test
    public void testAddInvalidProfessorStudent() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("1234", "Nume", 123, "a@b.c", "Prof123"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Nume profesor invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);

        service.del("1234");
    }

    @Test
    public void testAddInvalidGrupa2Student() {
        StudentRepo repo = new StudentRepo(new StudentValidator(),"studenti.xml");
        ServiceStudent service = new ServiceStudent(repo);

        int currentStud = repo.size();
        try{
            service.add(new Student("1234", "Nume", 903, "a@b.c", "Prof"));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("Grupa invalid"));
        }
        Assert.assertTrue(repo.size()==currentStud);

        service.del("1234");
    }



}
