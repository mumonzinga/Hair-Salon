import org.junit.Rule;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
public class StylistTest {


    @Rule
    public DatabaseRule database = new DatabaseRule();


    // testing save method
    @Test
    public void savingStylist_true() {
        Stylist stylist = new Stylist("Alpha","M","500");
        stylist.save();
        assertEquals("Alpha", stylist.getName());
    }
    // testing for update method
    @Test
    public void updatingStylist_true() {
        Stylist stylist = new Stylist("Ninja","M","500");
        stylist.save();
        stylist.update("Brandon", "M", "100");
        assertEquals("Brandon", Stylist.find(stylist.getId()).getName());
        assertEquals("M", Stylist.find(stylist.getId()).getGender());
        assertEquals("100", Stylist.find(stylist.getId()).getContact());
    }
    //find stlist test
    @Test
    public void findStylist_true() {
        Stylist stylist = new Stylist("Alpha","M","999");
        stylist.save();
        assertEquals("Alpha", Stylist.find(stylist.getId()).getName());
    }
    //delete stylist test
    @Test
    public void delete_deletesStylist_true() {
        Stylist stylist = new Stylist("Alpha", "M", "999");
        stylist.save();
        int stylistId = stylist.getId();
        stylist.delete();
        assertEquals(null, Stylist.find(stylistId));
    }

}