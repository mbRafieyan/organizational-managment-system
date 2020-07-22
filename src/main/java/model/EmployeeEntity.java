package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE", schema = "DOTIN")
public class EmployeeEntity {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private EmployeeEntity employeeManager;
    private List<EmailEntity> senderEmails;
    private List<EmailEntity> recieverEmails;
    private List<VacationsEntity> vacations;
    private CategoryElementEntity employeeRole;

    @Id
    @Column(name = "EMPLOYEEID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "oracleSequence")
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "EMAILADDRESS")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @OneToOne
    @Column(name = "MANAGERID")
    public EmployeeEntity getEmployeeManager() {
        return employeeManager;
    }

    public void setEmployeeManager(EmployeeEntity employeeManager) {
        this.employeeManager = employeeManager;
    }

    @OneToMany(mappedBy = "senderEmployee", cascade = CascadeType.ALL)
    public List<EmailEntity> getEmailEntityList() {
        return senderEmails;
    }

    public void setEmailEntityList(List<EmailEntity> emailEntityList) {
        this.senderEmails = emailEntityList;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    public List<VacationsEntity> getVacationsEntityList() {
        return vacations;
    }

    public void setVacationsEntityList(List<VacationsEntity> vacationsEntityList) {
        this.vacations = vacationsEntityList;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID")
    public CategoryElementEntity getCategoryElementEntity() {
        return employeeRole;
    }

    public void setCategoryElementEntity(CategoryElementEntity categoryElementEntity) {
        this.employeeRole = categoryElementEntity;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "RECIEVER",
            joinColumns = @JoinColumn(name = "EMPLOYEEID"),
            inverseJoinColumns = @JoinColumn(name = "EMAILID"))
    public List<EmailEntity> getRecieverEmails() {
        return recieverEmails;
    }

    public void setRecieverEmails(List<EmailEntity> recieverEmails) {
        this.recieverEmails = recieverEmails;
    }
}
