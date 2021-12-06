package com.resume.model.formation;

import com.resume.model.location.LocationDto;
import com.resume.model.enums.FormationTypeEnum;

import java.time.LocalDateTime;

public class FormationDto {

    private Integer formationId;

    private String formationTitle;

    private LocationDto location;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private FormationTypeEnum formationType;

    public FormationDto() {
    }

    public FormationDto(String formationTitle, LocationDto location, LocalDateTime startDate, LocalDateTime endDate, FormationTypeEnum formationTypeEnum) {
        this.formationTitle = formationTitle;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationTypeEnum;
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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
                ", formationTitle='"+ formationTitle +
                ", location='"+ location +
                ", startDate='"+ startDate +
                ", endDate='"+ endDate + '}';
    }

}
