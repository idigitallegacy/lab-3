package idigitallegacy.root.application.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cats", schema = "public", catalog = "postgres")
public class CatsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id", nullable = false)
    private Integer catId;
    @Basic
    @Column(name = "cat_name", nullable = false, length = 64)
    private String catName;
    @Basic
    @Column(name = "cat_birthday", nullable = false)
    private Date catBirthday;
    @Basic
    @Column(name = "cat_breed_id", nullable = false)
    private Integer catBreedId;
    @Basic
    @Column(name = "cat_color_id", nullable = false)
    private Integer catColorId;
    @Basic
    @Column(name = "cat_owner_id", nullable = false)
    private Integer catOwnerId;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Date getCatBirthday() {
        return catBirthday;
    }

    public void setCatBirthday(Date catBirthday) {
        this.catBirthday = catBirthday;
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

    public Integer getCatOwnerId() {
        return catOwnerId;
    }

    public void setCatOwnerId(Integer catOwnerId) {
        this.catOwnerId = catOwnerId;
    }
}
