package com.model;


import javax.persistence.*;

@Entity
@Table(name = "T_CATEGORYELEMENT", schema = "DOTIN")
public class CategoryElementEntity extends ParentEntity {

    @Basic
    @Column(name = "C_NAME")
    private String name;

    @Basic
    @Column(name = "C_CODE")
    private String code;

    @ManyToOne
    @JoinColumn(name = "C_CID")
    private CategoryEntity categoryEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

}
