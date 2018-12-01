package edu.matc.legendsmith.entity;

/**
 * The type Item price.
 */
public class ItemPrice {
    private int goldPrice;
    private int silverPrice;
    private int copperPrice;

    /**
     * Item price.
     */
    public void ItemPrice() {}

    /**
     * Item price.
     *
     * @param price the price
     */
    public void ItemPrice(int price) {
        setDenominationValues(price);
    }

    /**
     * This calculates and sets the object's gold, silver, and copper values from the given price, representing a sum total of all the coins.
     * Each coins is worth 1 copper, and each copper is worth 100 silver and each gold is worth 100 silver.
     *
     * @param price the price in total coins
     */
    public void setDenominationValues(int price) {
        goldPrice = price / 10000; //the result in gold, silver, and copper should be truncated, not rounded
        price = price - (goldPrice * 10000); //Subtract the gold value of the coins from the remaining price (same with silver)

        silverPrice = price / 100;
        price = price - (silverPrice * 100);

        copperPrice = price;
    }

    /**
     * Gets gold price.
     *
     * @return the gold price
     */
    public int getGoldPrice() {
        return goldPrice;
    }

    /**
     * Sets gold price.
     *
     * @param goldPrice the gold price
     */
    public void setGoldPrice(int goldPrice) {
        this.goldPrice = goldPrice;
    }

    /**
     * Gets silver price.
     *
     * @return the silver price
     */
    public int getSilverPrice() {
        return silverPrice;
    }

    /**
     * Sets silver price.
     *
     * @param silverPrice the silver price
     */
    public void setSilverPrice(int silverPrice) {
        this.silverPrice = silverPrice;
    }

    /**
     * Gets copper price.
     *
     * @return the copper price
     */
    public int getCopperPrice() {
        return copperPrice;
    }

    /**
     * Sets copper price.
     *
     * @param copperPrice the copper price
     */
    public void setCopperPrice(int copperPrice) {
        this.copperPrice = copperPrice;
    }

    @Override
    public String toString() {
        return "ItemPrice{" +
                "goldPrice=" + goldPrice +
                ", silverPrice=" + silverPrice +
                ", copperPrice=" + copperPrice +
                '}';
    }
}
