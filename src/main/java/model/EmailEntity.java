package model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "T_EMAIL", schema = "DOTIN")
public class EmailEntity extends ParentEntity {

    @Basic
    @Column(name = "C_SUBJECT")
    private String subject;

    @Basic
    @Column(name = "C_TEXT")
    private String text;

    @Lob
    @Basic
    @Column(name = "C_ATTACHMENT")
    private Blob attachment;

    @JoinColumn(name = "C_SENDERID")
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

    public Blob getAttachment() {
        return attachment;
    }

    public void setAttachment(Blob attachment) {
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
