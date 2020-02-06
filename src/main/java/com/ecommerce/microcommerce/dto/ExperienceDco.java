package com.ecommerce.microcommerce.dto;

import java.util.Date;

public class ExperienceDco {

    private Integer experienceId;

    private Integer organisationId;

    private Integer locationId;

    private Date startDate;

    private Date endDate;

    private String description;

    private String experienceType;

    public ExperienceDco(Integer experienceId, Integer organisationId, Integer locationId, Date startDate, Date endDate, String description, String experienceType) {
        this.experienceId = experienceId;
        this.organisationId = organisationId;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.experienceType = experienceType;
    }

    public ExperienceDco(Integer organisationId, Integer locationId, Date startDate, Date endDate, String description, String experienceType) {
        this.organisationId = organisationId;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.experienceType = experienceType;
    }

    public ExperienceDco() {
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public String getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(String experienceType) {
        this.experienceType = experienceType;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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
}
