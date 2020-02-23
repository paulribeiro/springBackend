package com.resume.dto;

import com.resume.model.enums.ExperienceTypeEnum;

import java.util.Date;

public class ExperienceDto {

    private Integer experienceId;

    private OrganisationDto organisationDto;

    private LocationDto locationDto;

    private Date startDate;

    private Date endDate;

    private String description;

    private ExperienceTypeEnum experienceType;

    public ExperienceDto() {
    }

    public ExperienceDto(OrganisationDto organisationDto, LocationDto locationDto, Date startDate, Date endDate, String description) {
        this.organisationDto = organisationDto;
        this.locationDto = locationDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public OrganisationDto getOrganisationDto() {
        return organisationDto;
    }

    public void setOrganisationDto(OrganisationDto organisationDto) {
        this.organisationDto = organisationDto;
    }

    public LocationDto getLocationDto() {
        return locationDto;
    }

    public void setLocationDto(LocationDto locationDto) {
        this.locationDto = locationDto;
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

    public ExperienceTypeEnum getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceTypeEnum experienceTypeEnum) {
        this.experienceType = experienceTypeEnum;
    }

    @Override
    public String toString(){
        return "Experience{"+
                "experienceId=" + experienceId +
                ", organisation='"+ organisationDto +
                ", location='"+ locationDto +
                ", startDate='"+ startDate +
                ", endDate='"+ endDate +
                ", description='"+ description +
                ", experienceType=" + experienceType + '}';
    }

}
