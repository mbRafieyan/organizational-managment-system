package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMAIL", schema = "DOTIN")
@PrimaryKeyJoinColumn(name = "ID")
public class EmailEntity extends ParentEntity{

    @Basic
    @Column(name = "SUBJECT")
    private String subject;

    @Basic
    @Column(name = "TEXT")
    private String text;

    @Basic
    @Column(name = "ATTACHMENT")
    private String attachment;

    @JoinColumn(name = "SENDERID")
    @ManyToOne
    private EmployeeEntity senderEmployee;

    @ManyToMany(mappedBy = "recieverEmails", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<EmployeeEntity> recievers;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public EmployeeEntity getEmployeeEntity() {
        return senderEmployee;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.senderEmployee = employeeEntity;
    }

    public List<EmployeeEntity> getRecievers() {
        return recievers;
    }

    public void setRecievers(List<EmployeeEntity> recievers) {
        this.recievers = recievers;
    }
}
