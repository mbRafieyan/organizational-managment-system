package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PARENT", schema = "DOTIN")
@Inheritance(strategy = InheritanceType.JOINED)
public class ParentEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "oracleSequence")
    private Long id;

    @Basic
    @Column(name = "ACTIVE")
    private Boolean active;

    @Basic
    @Column(name = "VERSION")
    private Float version;

    @Basic
    @Column(name = "CREATEDATE")
    private Date createDate;

    @Basic
    @Column(name = "MODIFIEDDATE")
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
