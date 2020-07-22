package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMAIL", schema = "DOTIN")
public class EmailEntity {

    private Long emailId;
    private String subject;
    private String text;
    private String attachment;
    private EmployeeEntity senderEmployee;
    private List<EmployeeEntity> recievers;

    @Id
    @Column(name = "EMAILID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "oracleSequence")
    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(long emailId) {
        this.emailId = emailId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDERID")
    public EmployeeEntity getEmployeeEntity() {
        return senderEmployee;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.senderEmployee = employeeEntity;
    }

    @Basic
    @Column(name = "ATTACHMENT")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @ManyToMany(mappedBy = "recieverEmails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<EmployeeEntity> getRecievers() {
        return recievers;
    }

    public void setRecievers(List<EmployeeEntity> recievers) {
        this.recievers = recievers;
    }
}
