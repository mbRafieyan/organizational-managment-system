package com.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_EMPLOYEE", schema = "DOTIN")
public class EmployeeEntity extends ParentEntity implements Serializable {

    @Basic
    @Column(name = "C_FIRSTNAME")
    private String firstName;

    @Basic
    @Column(name = "C_LASTNAME")
    private String lastName;

    @Basic
    @Column(name = "C_EMAILADDRESS")
    private String emailAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_ROLEID")
    private CategoryElementEntity employeeRole;

    @ManyToOne
    @JoinColumn(name = "C_MANAGERID")
    private EmployeeEntity employeeManager;

    @OneToMany(mappedBy = "employeeManager", cascade = CascadeType.ALL)
    private List<EmployeeEntity> managerEmployeesList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<VacationsEntity> vacations;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmployeeEntity getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeEntity employeeManager) {
        this.employeeManager = employeeManager;
    }

    public List<VacationsEntity> getVacationsEntityList() {
        return vacations;
    }

    public void setVacationsEntityList(List<VacationsEntity> vacationsEntityList) {
        this.vacations = vacationsEntityList;
    }

    public CategoryElementEntity getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(CategoryElementEntity categoryElementEntity) {
        this.employeeRole = categoryElementEntity;
    }

    public List<EmployeeEntity> getManagerEmployeesList() {
        return managerEmployeesList;
    }

    public void setManagerEmployeesList(List<EmployeeEntity> managerEmployeesList) {
        this.managerEmployeesList = managerEmployeesList;
    }
}