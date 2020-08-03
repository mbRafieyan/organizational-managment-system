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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "T_RECIEVER",
            joinColumns = @JoinColumn(name = "C_EMPLOYEEID"),
            inverseJoinColumns = @JoinColumn(name = "C_EMAILID"))
    private List<EmployeeEntity> recievers;

    @ManyToOne
    @JoinColumn(name = "C_SENDERID")
    private EmployeeEntity senderEmployee;

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
