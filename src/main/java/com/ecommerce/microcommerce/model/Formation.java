package com.ecommerce.microcommerce.model;

import com.ecommerce.microcommerce.model.enums.FormationTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RES_FORMATION")
public class Formation {

    @Id
    @GeneratedValue
    private Integer formationId;

    @Column(nullable = false)
    private String formationTitle;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID_FK", referencedColumnName = "locationId")
    private Location location;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormationTypeEnum formationType;

    public Formation() {
    }

    public Formation(String formationTitle, Location location, Date startDate, Date endDate, FormationTypeEnum formationType) {
        this.formationTitle = formationTitle;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationType;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public String getFormationTitle() {
        return formationTitle;
    }

    public void setFormationTitle(String formationTitle) {
        this.formationTitle = formationTitle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public FormationTypeEnum getFormationType() {
        return formationType;
    }

    public void setFormationType(FormationTypeEnum formationType) {
        this.formationType = formationType;
    }

    @Override
    public String toString(){
        return "Formation{"+
                "formationId=" + formationId +
                ", formationTitle="+ formationTitle +
                ", formaionType="+ formationType.toString() +
                ", location='"+ location +
                ", startDate='"+ startDate +
                ", endDate='"+ endDate + '}';
    }

}
