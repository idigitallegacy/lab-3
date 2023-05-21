package idigitallegacy.root.application.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "owners", schema = "public", catalog = "postgres")
public class OwnersEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_generator")
    @SequenceGenerator(name = "owners_generator", sequenceName = "owners_seq", allocationSize = 1)
    @Id
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
    @Basic
    @Column(name = "owner_name", nullable = false, length = 64)
    private String ownerName;
    @Basic
    @Column(name = "owner_birthday", nullable = false)
    private Date ownerBirthday;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getOwnerBirthday() {
        return ownerBirthday;
    }

    public void setOwnerBirthday(Date ownerBirthday) {
        this.ownerBirthday = ownerBirthday;
    }
}
