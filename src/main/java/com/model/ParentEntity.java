package com.model;

import javax.persistence.*;

@MappedSuperclass
public class ParentEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;

    @Basic
    @Column(name = "C_ACTIVE")
    private Boolean active;

    @Basic
    @Column(name = "C_VERSION")
    private int version;

    @Basic
    @Column(name = "C_CREATEDATE")
    private String createDate;

    @Basic
    @Column(name = "C_MODIFIEDDATE")
    private String modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
