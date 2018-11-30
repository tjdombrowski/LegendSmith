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
        I will want data on the following:
        -Elder Wood Planks
        -Mithril Ingots
        -Amalgamated Gemstones
        -T5 ingredients (bones for ?)
        -T6 ingredients
        -T4 mats
        -t3 mats
        -Orichalcum Ingots
        -Platinum Ingots
        -Mystic Coins


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
        */
    }

    @Test
    public void testRetrieveGw2CommerceResponse() {}
}