package com.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "T_EMAIL", schema = "DOTIN")
public class EmailEntity extends ParentEntity implements Serializable {

    @Basic
    @Column(name = "C_SUBJECT")
    @NotBlank(message = "Subject is a required field")
    private String subject;

    @Basic
    @Column(name = "C_TEXT")
    private String text;

    @Lob
    @Basic
    @Column(name = "C_ATTACHMENT")
    private Blob attachment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "T_RECIEVER",
            joinColumns = @JoinColumn(name = "C_EMAILID"),
            inverseJoinColumns = @JoinColumn(name = "C_EMPLOYEEID"))
    @NotEmpty(message = "Recievers is a required field")
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

    public EmployeeEntity getSenderEmployee() {
        return senderEmployee;
    }

    public void setSenderEmployee(EmployeeEntity senderEmployee) {
        this.senderEmployee = senderEmployee;
    }

    public List<EmployeeEntity> getRecievers() {
        return recievers;
    }

    public void setRecievers(List<EmployeeEntity> recievers) {
        this.recievers = recievers;
    }
}
