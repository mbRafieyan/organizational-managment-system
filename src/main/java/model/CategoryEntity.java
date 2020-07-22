package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY", schema = "DOTIN")
public class CategoryEntity {

    private Long categoryId;
    private String categoryName;
    private List<CategoryElementEntity> categoryElements;

    @Id
    @Column(name = "CATEGORYID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "oracleSequence")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CATEGORYNAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(mappedBy = "categoryEntity" ,cascade = CascadeType.ALL)
    public List<CategoryElementEntity> getCategoryElementEntityList() {
        return categoryElements;
    }

    public void setCategoryElementEntityList(List<CategoryElementEntity> categoryElementEntityList) {
        this.categoryElements = categoryElementEntityList;
    }
}
