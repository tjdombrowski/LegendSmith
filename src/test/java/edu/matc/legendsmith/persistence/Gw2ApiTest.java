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
    private Gw2ApiUser gw2ApiUser;

    @BeforeEach
    public void setUp() {
        gw2ApiUser = new Gw2ApiUser();
    }

    /**
     * Tests whether a sell order price is properly retrieved with Gw2ApiUser.
     */
    @Test
    public void testSellOrderResponseMethod() {
        int price = 0;

        price = gw2ApiUser.getSellOrderPrice(19709);

        assertNotEquals(0, price);
    }

    /**
     * Tests whether retrieving the API's sell order price and converting it into gold, silver, and copper as an ItemPrice
     * works correctly.
     */
    @Test
    public void testItemPriceConversionFromSellPrice() {
        Gw2ApiUser gw2ApiUser = new Gw2ApiUser();
        int price = 0;

        price = gw2ApiUser.getSellOrderPrice(68063);

        assertNotEquals(0, price);

        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setDenominationValues(price);

        assertTrue(itemPrice.getGoldPrice() >= 0 && itemPrice.getGoldPrice() <= 100);
        assertTrue(itemPrice.getSilverPrice() >= 0 && itemPrice.getSilverPrice() <= 100);
        assertTrue(itemPrice.getCopperPrice() >= 0 && itemPrice.getCopperPrice() <= 100);

        System.out.println(itemPrice);

    }
}