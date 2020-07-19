package model;

import javax.persistence.*;

@Entity
@Table(name = "EMAIL", schema = "DOTIN")
public class EmailEntity {

    private Long emailId;
    private String distAddress;
    private String subject;
    private String text;
    private EmployeeEntity employee;

    @Id
    @Column(name = "EMAILID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "DOTINSEQUENCE", allocationSize = 1)
    public long getEmailId() {
        return emailId;
    }

    public void setEmailId(long emailid) {
        this.emailId = emailid;
    }

    @Basic
    @Column(name = "DISTADDRESS")
    public String getDistAddress() {
        return distAddress;
    }

    public void setDistAddress(String distaddress) {
        this.distAddress = distaddress;
    }

    @Basic
    @Column(name = "SUBJECT")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "SOURCEID")
    public EmployeeEntity getEmployeeEntity() {
        return employee;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employee = employeeEntity;
    }

}
