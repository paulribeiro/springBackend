package com.ecommerce.microcommerce.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RES_EXPERIENCE")
public class Experience {

    @Id
    @GeneratedValue
    private Integer experienceId;

    @ManyToOne
    private Organisation organisation;

    @ManyToOne
    private Location location;

    private Date startDate;

    private Date endDate;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExperienceType experienceType;

    public Experience() {
    }

    public Experience(Organisation organisation, Location location, Date startDate, Date endDate, String description) {
        this.organisation = organisation;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    @Override
    public String toString(){
        return "Experience{"+
                "experienceId=" + experienceId +
                ", organisation='"+ organisation +
                ", location='"+ location +
                ", startDate='"+ startDate +
                ", endDate='"+ endDate +
                ", description='"+ description +
                ", experienceType=" + experienceType+ '}';
    }

}
