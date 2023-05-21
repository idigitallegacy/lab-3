package idigitallegacy.root.application.entity;

import idigitallegacy.root.application.entity.key.FriendCatsEntityPK;
import jakarta.persistence.*;

@Entity
@Table(name = "friend_cats", schema = "public", catalog = "postgres")
@IdClass(FriendCatsEntityPK.class)
public class FriendCatsEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_cat_generator")
    @SequenceGenerator(name = "friend_cat_generator", sequenceName = "friend_cat_seq", allocationSize = 1)
    @Id
    @Column(name = "cat_id", nullable = false)
    private Integer catId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend2_cat_generator")
    @SequenceGenerator(name = "friend2_cat_generator", sequenceName = "friend2_cat_seq", allocationSize = 1)
    @Id
    @Column(name = "friend_id", nullable = false)
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
}
