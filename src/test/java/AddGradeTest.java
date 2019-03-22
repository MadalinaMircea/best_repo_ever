import Domain.Teme;
import Repository.TemeRepo;
import Service.ServiceTeme;
import Validator.TemeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AddThemeTest {

    @Test
    public void testAddTheme() {
        TemeRepo repo = new TemeRepo(new TemeValidator(),"teme.xml");
        ServiceTeme service = new ServiceTeme(repo);

        service.del(15);

        assertEquals(service.find(15), null);

        service.add(new Teme(15, "Desc", 1, 2));

        assertNotEquals(service.find(15), null);
    }
}
