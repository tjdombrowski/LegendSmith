package restactivity.persistence;

import com.arenanet.guildwars2.Results;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Gw2ApiTest {

    @Test
    public void testGW2API() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.guildwars2.com/v2/minis/424");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        String expectedResponse = "{\n" +
                "  \"id\": 424,\n" +
                "  \"name\": \"Mini Aurene\",\n" +
                "  \"icon\": \"https://render.guildwars2.com/file/3359C5F30AE534F513ABF603023194F0E703D2C3/1601469.png\",\n" +
                "  \"order\": 429,\n" +
                "  \"item_id\": 79766\n" +
                "}";
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGW2APConvertJsonI() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.guildwars2.com/v2/minis/424");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Results results = mapper.readValue(response, Results.class);

        Results expectedResults = new Results(424, "Mini Aurene",
                "https://render.guildwars2.com/file/3359C5F30AE534F513ABF603023194F0E703D2C3/1601469.png",
                429, 79766);

        assertEquals(424, results.getId());
        assertEquals("Mini Aurene", results.getName());
    }
}