package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORYELEMENT", schema = "DOTIN")
@PrimaryKeyJoinColumn(name = "ID")
public class CategoryElementEntity extends ParentEntity {

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "CODE")
    private String code;

    @ManyToOne
    @JoinColumn(name = "CID")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "employeeRole", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

    @OneToMany(mappedBy = "vacationTypeCee", cascade = CascadeType.ALL)
    private List<VacationsEntity> typeVacations;

    @OneToMany(mappedBy = "vacationStatusCee", cascade = CascadeType.ALL)
    private List<VacationsEntity> statusVacations;

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

    public List<EmployeeEntity> getEmployeeEntityList() {
        return employees;
    }

    public void setEmployeeEntityList(List<EmployeeEntity> employeeEntityList) {
        this.employees = employeeEntityList;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }


    public List<VacationsEntity> getTypeVacationsEntityList() {
        return typeVacations;
    }

    public void setTypeVacationsEntityList(List<VacationsEntity> typeVacationsEntityList) {
        this.typeVacations = typeVacationsEntityList;
    }

    public List<VacationsEntity> getStatusvacationEntityList() {
        return statusVacations;
    }

    public void setStatusvacationEntityList(List<VacationsEntity> statusVacationEntityList) {
        this.statusVacations = statusVacationEntityList;
    }
}
