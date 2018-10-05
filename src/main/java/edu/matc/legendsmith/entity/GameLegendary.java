package edu.matc.legendsmith.entity;

/**
 * The type Game legendary.
 */
public class GameLegendary {

    private String gameName;
    private int legendaryId;

    /**
     * Gets game name.
     *
     * @return the game name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Sets game name.
     *
     * @param gameName the game name
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Gets legendary id.
     *
     * @return the legendary id
     */
    public int getLegendaryId() {
        return legendaryId;
    }

    /**
     * Sets legendary id.
     *
     * @param legendaryId the legendary id
     */
    public void setLegendaryId(int legendaryId) {
        this.legendaryId = legendaryId;
    }

    /**
     * Returns a string of GameLegendary info.
     *
     * @return a string consisting of GameLegendary info
     */
    @Override
    public String toString() {
        return "GameLegendary{" +
                "gameName='" + gameName + '\'' +
                ", legendaryId=" + legendaryId +
                '}';
    }

}
