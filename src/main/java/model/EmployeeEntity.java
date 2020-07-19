package model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE", schema = "DOTIN")
public class EmployeeEntity implements Serializable {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long manageId;
    private List<EmailEntity> emails;
    private List<VacationsEntity> vacations;
    private CategoryElementEntity categoryElement;

    @Id
    @Column(name = "EMPLOYEEID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "DOTINSEQUENCE", allocationSize = 1)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeid) {
        this.employeeId = employeeid;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    @Basic
    @Column(name = "EMAILADDRESS")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailaddress) {
        this.emailAddress = emailaddress;
    }

    @Basic
    @Column(name = "MANAGEID")
    public Long getManageId() {
        return manageId;
    }

    public void setManageId(Long manageid) {
        this.manageId = manageid;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<EmailEntity> getEmailEntityList() {
        return emails;
    }

    public void setEmailEntityList(List<EmailEntity> emailEntityList) {
        this.emails = emailEntityList;
    }

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public List<VacationsEntity> getVacationsEntityList() {
        return vacations;
    }

    public void setVacationsEntityList(List<VacationsEntity> vacationsEntityList) {
        this.vacations = vacationsEntityList;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "ROLEID")
    public CategoryElementEntity getCategoryElementEntity() {
        return categoryElement;
    }

    public void setCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        this.categoryElement = categoryElementEntity;
    }
}
