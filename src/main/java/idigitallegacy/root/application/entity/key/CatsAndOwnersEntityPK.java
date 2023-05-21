package idigitallegacy.root.application.entity.key;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class CatsAndOwnersEntityPK implements Serializable {
    @Column(name = "cat_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cao_cat_id_generator")
    @SequenceGenerator(name = "cao_cat_id_generator", sequenceName = "cao_cat_id_seq", allocationSize = 1)
    private int catId;
    @Column(name = "owner_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cao_owner_id_generator")
    @SequenceGenerator(name = "cao_owner_id_generator", sequenceName = "cao_owner_id_seq", allocationSize = 1)
    private int ownerId;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int catBreedId) {
        this.ownerId = catBreedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatsAndOwnersEntityPK that = (CatsAndOwnersEntityPK) o;
        return catId == that.catId && ownerId == that.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, ownerId);
    }
}