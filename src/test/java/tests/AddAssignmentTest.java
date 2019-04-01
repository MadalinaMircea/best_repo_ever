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
}
