package idigitallegacy.root.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "colors", schema = "public", catalog = "postgres")
public class ColorsEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colors_generator")
    @SequenceGenerator(name = "colors_generator", sequenceName = "colors_seq", allocationSize = 1)
    @Id
    @Column(name = "color_id", nullable = false)
    private Integer colorId;
    @Basic
    @Column(name = "color_name", nullable = false, length = 64)
    private String colorName;

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
