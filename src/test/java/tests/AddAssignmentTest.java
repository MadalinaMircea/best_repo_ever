package tests;

import Domain.Teme;
import Repository.TemeRepo;
import Service.ServiceTeme;
import Validator.TemeValidator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddAssignmentTest {

    @Test
    public void testAddAssignment() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        int previousSize = repo.size();
        try{
            service.add(new Teme(1, "Desc", -1, 2));
        }
        catch(Exception ex){
            assertTrue(ex.getMessage().contains("\nSaptamana in care tema a fost primita este invalida"));
        }
        Assert.assertTrue(repo.size()==previousSize);


        service.del(1);
    }

    @Test
    public void testAddAssignmentCorrect() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        int previousSize = repo.size();
        service.add(new Teme(1, "Desc", 1, 2));
        Assert.assertTrue(repo.size()==previousSize + 1);

        service.del(1);
    }

    @Test
    public void testAddAssignmentNullID() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        int previousSize = repo.size();
        try
        {
            service.add(new Teme(null, "Desc", 1, 2));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("ID invalid"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }

    @Test
    public void testAddAssignmentNegativeID() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        int previousSize = repo.size();
        try
        {
            service.add(new Teme(-5, "Desc", 1, 2));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("ID invalid"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }

    @Test
    public void testAddAssignmentGreaterDeadline() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);
        int previousSize = repo.size();
        try
        {
            service.add(new Teme(1, "Desc", 1, 20));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("Deadline invalid"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }

    @Test
    public void testAddAssignmentNegativeDeadline() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);
        int previousSize = repo.size();
        try
        {
            service.add(new Teme(1, "Desc", 1, -20));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("Deadline invalid"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }

    @Test
    public void testAddAssignmentLowerDeadline() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);
        int previousSize = repo.size();
        try
        {
            service.add(new Teme(1, "Desc", 5, 2));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("Deadline invalid"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }

    @Test
    public void testAddAssignmentGreaterPrimire() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);
        int previousSize = repo.size();
        try
        {
            service.add(new Teme(1, "Desc", 20, 2));
        }
        catch(Exception e)
        {
            assertTrue(e.getMessage().contains("Saptamana in care tema a fost primita este invalida"));
        }
        Assert.assertTrue(repo.size()==previousSize);
    }
}
