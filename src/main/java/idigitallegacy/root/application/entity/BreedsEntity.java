package idigitallegacy.root.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "breeds", schema = "public", catalog = "postgres")
public class BreedsEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breeds_generator")
    @SequenceGenerator(name = "breeds_generator", sequenceName = "breeds_seq", allocationSize = 1)
    @Id
    @Column(name = "breed_id", nullable = false)
    private int breedId;
    @Basic
    @Column(name = "breed_name", nullable = false, length = 64)
    private String breedName;

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
