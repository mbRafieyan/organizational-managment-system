package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VACATIONS", schema = "DOTIN")
public class VacationsEntity {

    private Long vacationId;
    private Date vacationStart;
    private Date vacationEnd;
    private EmployeeEntity employee;
    private CategoryElementEntity vacationTypeCee;
    private CategoryElementEntity vacationStatusCee;

    @Id
    @Column(name = "VACATIONID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "oracleSequence")
    public Long getVacationId() {
        return vacationId;
    }

    public void setVacationId(long vacationId) {
        this.vacationId = vacationId;
    }

    @Basic
    @Column(name = "VACATIONSTART")
    @Temporal(TemporalType.DATE)
    public Date getVacationStart() {
        return vacationStart;
    }

    public void setVacationStart(Date vacationStart) {
        this.vacationStart = vacationStart;
    }

    @Basic
    @Column(name = "VACATIONEND")
    @Temporal(TemporalType.DATE)
    public Date getVacationEnd() {
        return vacationEnd;
    }

    public void setVacationEnd(Date vacationEnd) {
        this.vacationEnd = vacationEnd;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTEDEMPLOYEEID")
    public EmployeeEntity getEmployeeEntity() {
        return employee;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employee = employeeEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACATIONTYPE")
    public CategoryElementEntity getVacationTypeCee() {
        return vacationTypeCee;
    }

    public void setVacationTypeCee(CategoryElementEntity vacationTypeCee) {
        this.vacationTypeCee = vacationTypeCee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACATIONSTATUS")
    public CategoryElementEntity getVacationStatusCee() {
        return vacationStatusCee;
    }

    public void setVacationStatusCee(CategoryElementEntity vacationStatusCee) {
        this.vacationStatusCee = vacationStatusCee;
    }
}
