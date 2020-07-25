package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY", schema = "DOTIN")
@PrimaryKeyJoinColumn(name = "ID")
public class CategoryEntity extends ParentEntity{

    @Basic
    @Column(name = "CATEGORYNAME")
    private String categoryName;

    @OneToMany(mappedBy = "categoryEntity" ,cascade = CascadeType.ALL)
    private List<CategoryElementEntity> categoryElements;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryElementEntity> getCategoryElementEntityList() {
        return categoryElements;
    }

    public void setCategoryElementEntityList(List<CategoryElementEntity> categoryElementEntityList) {
        this.categoryElements = categoryElementEntityList;
    }
}
