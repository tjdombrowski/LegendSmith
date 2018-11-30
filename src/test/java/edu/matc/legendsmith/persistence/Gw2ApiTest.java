package edu.matc.legendsmith.persistence;

import com.arenanet.gw2.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class Gw2ApiTest {

    /**
     * Tests whether the commerce/prices results are being correctly retrieved and mapped.
     *
     * @throws Exception
     */
    @Test
    public void testRetrieveGw2CommerceResponse() throws Exception {
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
            -incandescent dust ngs                    24356
            -large claws                    24350
            -intricate totems               24299
            -potent venom sacs              24282
            -large scales                   24288
        -T6 ingredients
        -T4 mats
        -t3 mats
        */

        Client client = ClientBuilder.newClient();

        WebTarget target =
                client.target("https://api.guildwars2.com/v2/commerce/prices/19684");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Response results = mapper.readValue(response, Response.class);

        assertEquals(19684, results.getId());
        assertFalse(results.isWhitelisted());
        assertNotNull(results.getBuys()); // The response for the buy and sell orders are going to change, so I'm just checking whether or not I'm getting data back
        assertNotNull(results.getSells());

    }

}