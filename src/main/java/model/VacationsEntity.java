package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VACATIONS", schema = "DOTIN")
@PrimaryKeyJoinColumn(name = "ID")
public class VacationsEntity extends ParentEntity{

    @Basic
    @Column(name = "VACATIONSTART")
    @Temporal(TemporalType.DATE)
    private Date vacationStart;

    @Basic
    @Column(name = "VACATIONEND")
    @Temporal(TemporalType.DATE)
    private Date vacationEnd;

    @ManyToOne
    @JoinColumn(name = "REQUESTEDEMPLOYEEID")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "VACATIONTYPE")
    private CategoryElementEntity vacationTypeCee;

    @ManyToOne
    @JoinColumn(name = "VACATIONSTATUS")
    private CategoryElementEntity vacationStatusCee;

    public Date getVacationStart() {
        return vacationStart;
    }

    public void setVacationStart(Date vacationStart) {
        this.vacationStart = vacationStart;
    }

    public Date getVacationEnd() {
        return vacationEnd;
    }

    public void setVacationEnd(Date vacationEnd) {
        this.vacationEnd = vacationEnd;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employee;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
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
