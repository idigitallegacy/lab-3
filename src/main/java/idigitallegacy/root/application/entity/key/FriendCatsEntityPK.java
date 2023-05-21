package idigitallegacy.root.application.entity.key;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class FriendCatsEntityPK implements Serializable {
    @Column(name = "cat_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_cat_generator")
    @SequenceGenerator(name = "friend_cat_generator", sequenceName = "friend_cat_seq", allocationSize = 1)
    private Integer catId;
    @Column(name = "friend_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend2_cat_generator")
    @SequenceGenerator(name = "friend2_cat_generator", sequenceName = "friend2_cat_seq", allocationSize = 1)
    private Integer friendId;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendCatsEntityPK that = (FriendCatsEntityPK) o;
        return catId == that.catId && friendId == that.friendId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, friendId);
    }
}
