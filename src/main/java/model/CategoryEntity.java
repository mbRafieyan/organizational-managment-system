package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY", schema = "DOTIN", catalog = "")
public class CategoryEntity {

    private Long categoryId;
    private String categoryName;
    private List<CategoryElementEntity> categoryElements;

    @Id
    @Column(name = "CATEGORYID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "DOTINSEQUENCE", allocationSize = 1)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryid) {
        this.categoryId = categoryid;
    }

    @Basic
    @Column(name = "CATEGORYNAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryname) {
        this.categoryName = categoryname;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<CategoryElementEntity> getCategoryElementEntityList() {
        return categoryElements;
    }

    public void setCategoryElementEntityList(List<CategoryElementEntity> categoryElementEntityList) {
        this.categoryElements = categoryElementEntityList;
    }
}
