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
import java.util.Properties;

public class Gw2ApiUser implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String propertiesPath = "/legendsmith.properties";
    private String gw2ApiUrl;

    public Gw2ApiUser() {
        Properties properties = loadProperties(propertiesPath);
        gw2ApiUrl = properties.getProperty("gw2.commerce.api.url");
    }

    /**
     * Calls the Guild Wars 2 API and retrieves the lowest sell order (sell orders are the listed items) in coins.
     * This may return a 0 if something goes wrong with the response, which will cause all task costs generated to show as a 0.
     *
     * @param gw2ItemId
     * @return sellOrderPrice the sell order price
     */
    public int getSellOrderPrice(int gw2ItemId) {
        int sellOrderPrice = 0;

        Client client = ClientBuilder.newClient();

        WebTarget target =
                client.target(gw2ApiUrl + gw2ItemId);
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

        logger.debug(sellOrderPrice);
        return sellOrderPrice;
    }

}