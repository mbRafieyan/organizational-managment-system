package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORYELEMENT", schema = "DOTIN")
public class CategoryElementEntity {

    private Long categoryElementId;
    private String name;
    private String code;
    private CategoryEntity categoryEntity;
    private List<EmployeeEntity> employees;
    private List<VacationsEntity> typeVacations;
    private List<VacationsEntity> statusVacations;

    @Id
    @Column(name = "CATEGORYELEMENTID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "DOTINSEQUENCE", allocationSize = 1)
    public long getCategoryElementId() {
        return categoryElementId;
    }

    public void setCategoryElementId(long categoryelementid) {
        this.categoryElementId = categoryelementid;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<EmployeeEntity> getEmployeeEntityList() {
        return employees;
    }

    public void setEmployeeEntityList(List<EmployeeEntity> employeeEntityList) {
        this.employees = employeeEntityList;
    }

    @ManyToOne
    @JoinColumn(name = "CID")
    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<VacationsEntity> getTypeVacationsEntityList() {
        return typeVacations;
    }

    public void setTypeVacationsEntityList(List<VacationsEntity> typeVacationsEntityList) {
        this.typeVacations = typeVacationsEntityList;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<VacationsEntity> getStatusvacationEntityList() {
        return statusVacations;
    }

    public void setStatusvacationEntityList(List<VacationsEntity> statusvacationEntityList) {
        this.statusVacations = statusvacationEntityList;
    }
}
