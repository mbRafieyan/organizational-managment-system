package com.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "T_VACATIONS", schema = "DOTIN")
public class VacationsEntity extends ParentEntity {

    @Basic
    @Column(name = "C_VACATIONSTART")
    @NotBlank(message = "Vacation start is a required field")
    private String vacationStart;

    @Basic
    @Column(name = "C_VACATIONEND")
    @NotBlank(message = "Vacation end is a required field")
    private String vacationEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_REQUESTEDEMPLOYEEID")
    @NotNull(message = "Employee is a required field")
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_VACATIONTYPE")
    private CategoryElementEntity vacationTypeCee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "C_VACATIONSTATUS")
    private CategoryElementEntity vacationStatusCee;

    public String getVacationStart() {
        return vacationStart;
    }

    public void setVacationStart(String vacationStart) {
        this.vacationStart = vacationStart;
    }

    public String getVacationEnd() {
        return vacationEnd;
    }

    public void setVacationEnd(String vacationEnd) {
        this.vacationEnd = vacationEnd;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employeeEntity) {
        this.employee = employeeEntity;
    }

    public CategoryElementEntity getVacationTypeCee() {
        return vacationTypeCee;
    }

    public void setVacationTypeCee(CategoryElementEntity vacationTypeCee) {
        this.vacationTypeCee = vacationTypeCee;
    }

    public CategoryElementEntity getVacationStatusCee() {
        return vacationStatusCee;
    }

    public void setVacationStatusCee(CategoryElementEntity vacationStatusCee) {
        this.vacationStatusCee = vacationStatusCee;
    }
}
