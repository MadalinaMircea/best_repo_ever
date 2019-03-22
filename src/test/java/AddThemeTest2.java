import Domain.Teme;
import Repository.TemeRepo;
import Service.ServiceTeme;
import Validator.TemeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddThemeTest2 {

    @Test
    public void testAddTheme2() {
        TemeRepo repo = new TemeRepo(new TemeValidator(), "teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        service.add(new Teme(25, "Desc", 1, 2));

        assertEquals(service.find(25).getID().intValue(), 25);
        assertEquals(service.find(25).getDescriere(), "Desc");
        assertEquals(service.find(25).getSapt_primire().intValue(), 1);
        assertEquals(service.find(25).getDeadline().intValue(), 2);
    }
}
