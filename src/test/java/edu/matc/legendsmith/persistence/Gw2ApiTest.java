package edu.matc.legendsmith.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Gw2ApiTest {

    @Test
    public void testRetrieveGw2ItemResponse() throws Exception {
        /*
        TODO: I will want data on the following:
        -Elder Wood Planks                  19709
        -Mithril Ingots                     19684
        -Orichalcum Ingots                  19685
        -Platinum Ingots                    19686
        -Darksteel Ingots                   19681
        -Iron Ingots                        19683
        -Deldrimor Steel Ingots             46738
        -Mystic Coins                       19976
        -Amalgamated Gemstones              68063
        -T5 ingredients (bones for ?)
            -large bones                    24341
            -incandescent dust              24276
            -vial of potent blood           24294
            -large fangs                    24356
            -large claws                    24350
            -intricate totems               24299
            -potent venom sacs              24282
            -large scales                   24288
        -T6 ingredients
        -T4 mats
        -t3 mats



        Client client = ClientBuilder.newClient();

        WebTarget target =
                client.target("https://api.guildwars2.com/v2/commerce/prices/ID");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Results results = mapper.readValue(response, Results.class);

        Results expectedResults = new Results(424, "Mini Aurene",
                "https://render.guildwars2.com/file/3359C5F30AE534F513ABF603023194F0E703D2C3/1601469.png",
                429, 79766);

        assertEquals(424, results.getId());
        assertEquals("Mini Aurene", results.getName());
        */
    }

    @Test
    public void testRetrieveGw2CommerceResponse() {}
}