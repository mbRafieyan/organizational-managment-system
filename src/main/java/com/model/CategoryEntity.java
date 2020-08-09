package com.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CATEGORY", schema = "DOTIN")
public class CategoryEntity extends ParentEntity {

    @Basic
    @Column(name = "C_CATEGORYNAME")
    private String categoryName;

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
