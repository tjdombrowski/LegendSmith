package edu.matc.legendsmith.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The type User legendary id.
 */
@Embeddable
public class UserLegendaryId implements Serializable {

    @Column(name = "userId")
    private int userId;

    @Column(name = "legendaryId")
    private int legendaryId;

    private UserLegendaryId() {}

    public UserLegendaryId(int userId, int legendaryId) {
        this.userId = userId;
        this.legendaryId = legendaryId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLegendaryId that = (UserLegendaryId) o;

        if (userId != that.userId) return false;
        return legendaryId == that.legendaryId;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + legendaryId;
        return result;
    }
}
