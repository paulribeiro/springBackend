package com.resume.dto;

import com.resume.model.enums.FormationTypeEnum;

import java.util.Date;

public class FormationDto {

    private Integer formationId;

    private String formationTitle;

    private LocationDto locationDto;

    private Date startDate;

    private Date endDate;

    private FormationTypeEnum formationTypeEnum;

    public FormationDto() {
    }

    public FormationDto(String formationTitle, LocationDto locationDto, Date startDate, Date endDate, FormationTypeEnum formationTypeEnum) {
        this.formationTitle = formationTitle;
        this.locationDto = locationDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationTypeEnum = formationTypeEnum;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public String getFormationTitle() {
        return formationTitle;
    }

    public void setFormationTitle(String formationTitle) {
        this.formationTitle = formationTitle;
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

    public FormationTypeEnum getFormationTypeEnum() {
        return formationTypeEnum;
    }

    public void setFormationTypeEnum(FormationTypeEnum formationTypeEnum) {
        this.formationTypeEnum = formationTypeEnum;
    }

    @Override
    public String toString(){
        return "Formation{"+
                "formationId=" + formationId +
                ", formationTitle='"+ formationTitle +
                ", location='"+ locationDto +
                ", startDate='"+ startDate +
                ", endDate='"+ endDate + '}';
    }

}
