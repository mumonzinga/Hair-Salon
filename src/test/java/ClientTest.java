import org.junit.Rule;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
public class ClientTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    //testing save method
    @Test
    public void savingClient_true() {
        Client client = new Client("Ninja","M","100", 2);
        client.save();
        assertEquals("Ninja", client.getName());
    }
    // find client test
    @Test
    public void findCLient_true() {
        Client client = new Client("Ninja","M","100", 2);
        client.save();
        assertEquals("Ninja", Client.find(client.getId()).getName());
    }
    // updating client test
    @Test
    public void updatingClient_true() {
        Client client = new Client("Ninja","M","100", 2);
        client.save();
        client.update("Brandon", "M", "100", 2);
        assertEquals("Brandon", Client.find(client.getId()).getName());
        assertEquals("M", Client.find(client.getId()).getGender());
        assertEquals("100", Client.find(client.getId()).getContact());
        assertEquals(2, Client.find(client.getId()).getStylist());
    }
    //delete client test
    @Test
    public void delete_deletesClient_true() {
        Client client = new Client("Ninja","M","100", 2);
        client.save();
        int clientId = client.getId();
        client.delete();
        assertEquals(null, Client.find(clientId));
    }

}