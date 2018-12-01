package edu.matc.legendsmith.persistence;

import com.arenanet.gw2.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class Gw2ApiUser {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String GW2API_URL = "https://api.guildwars2.com/v2/commerce/prices/";

    /**
     * Calls the Guild Wars 2 API and retrieves the lowest sell order (sell orders are the listed items) in coins.
     *
     * @param gw2ItemId
     * @return sellOrderPrice the sell order price
     */
    public int getSellOrderPrice(int gw2ItemId) {
        int sellOrderPrice = 0;

        Client client = ClientBuilder.newClient();

        WebTarget target =
                client.target(GW2API_URL + gw2ItemId);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Response results = mapper.readValue(response, Response.class);

            sellOrderPrice = results.getSells().getUnitPrice();
        } catch (IOException ioEx) {
            logger.error(ioEx);
        } catch (Exception ex) {
            logger.error(ex);
        }

        return sellOrderPrice;
    }

}