package idigitallegacy.root.application.entity;

import idigitallegacy.root.application.entity.key.CatsAndOwnersEntityPK;
import jakarta.persistence.*;

@Entity
@Table(name = "cats_and_owners", schema = "public", catalog = "postgres")
@IdClass(CatsAndOwnersEntityPK.class)
public class CatsAndOwnersEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cao_cat_id_generator")
    @SequenceGenerator(name = "cao_cat_id_generator", sequenceName = "cao_cat_id_seq", allocationSize = 1)
    @Id
    @Column(name = "cat_id", nullable = false)
    private Integer catId;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cao_breed_id_generator")
    @SequenceGenerator(name = "cao_breed_id_generator", sequenceName = "cao_breed_id_seq", allocationSize = 1)
    @Id
    @Column(name = "cat_breed_id", nullable = false)
    private Integer catBreedId;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cao_cat_color_id_generator")
    @SequenceGenerator(name = "cao_cat_color_id_generator", sequenceName = "cao_cat_color_id_seq", allocationSize = 1)
    @Id
    @Column(name = "cat_color_id", nullable = false)
    private Integer catColorId;
    @Basic
    @Column(name = "owner_id", nullable = true)
    private Integer ownerId;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatBreedId() {
        return catBreedId;
    }

    public void setCatBreedId(Integer catBreedId) {
        this.catBreedId = catBreedId;
    }

    public Integer getCatColorId() {
        return catColorId;
    }

    public void setCatColorId(Integer catColorId) {
        this.catColorId = catColorId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}

