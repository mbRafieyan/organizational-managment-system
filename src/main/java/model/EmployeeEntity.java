package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE", schema = "DOTIN")
@PrimaryKeyJoinColumn(name = "ID")
public class EmployeeEntity extends ParentEntity{

    @Basic
    @Column(name = "FIRSTNAME")
    private String firstName;

    @Basic
    @Column(name = "LASTNAME")
    private String lastName;

    @Basic
    @Column(name = "EMAILADDRESS")
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "MANAGERID")
    private EmployeeEntity employeeManager;

    @OneToMany(mappedBy = "employeeManager", cascade = CascadeType.ALL)
    private List<EmployeeEntity> managerEmployeesList;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "RECIEVER",
            joinColumns = @JoinColumn(name = "EMPLOYEEID"),
            inverseJoinColumns = @JoinColumn(name = "EMAILID"))
    private List<EmailEntity> recieverEmails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<VacationsEntity> vacations;

    @ManyToOne
    @JoinColumn(name = "ROLEID")
    private CategoryElementEntity employeeRole;

    @OneToMany(mappedBy = "senderEmployee", cascade = CascadeType.ALL)
    private List<EmailEntity> senderEmails;

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

    public List<EmailEntity> getEmailEntityList() {
        return senderEmails;
    }

    public void setEmailEntityList(List<EmailEntity> emailEntityList) {
        this.senderEmails = emailEntityList;
    }

    public List<VacationsEntity> getVacationsEntityList() {
        return vacations;
    }

    public void setVacationsEntityList(List<VacationsEntity> vacationsEntityList) {
        this.vacations = vacationsEntityList;
    }

    public CategoryElementEntity getCategoryElementEntity() {
        return employeeRole;
    }

    public void setCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        this.employeeRole = categoryElementEntity;
    }

    public List<EmailEntity> getRecieverEmails() {
        return recieverEmails;
    }

    public void setRecieverEmails(List<EmailEntity> recieverEmails) {
        this.recieverEmails = recieverEmails;
    }

    public List<EmployeeEntity> getManagerEmployeesList() {
        return managerEmployeesList;
    }

    public void setManagerEmployeesList(List<EmployeeEntity> managerEmployeesList) {
        this.managerEmployeesList = managerEmployeesList;
    }
}
