package com.resume.dco;

import com.resume.model.enums.FormationTypeEnum;
import com.resume.validator.interfaces.ValueOfEnum;

import java.util.Date;

public class FormationDco {

    private Integer formationId;

    private String formationTitle;

    private Integer locationId;

    private Date startDate;

    private Date endDate;

    @ValueOfEnum(enumClass = FormationTypeEnum.class)
    private String formationType;

    public FormationDco(Integer formationId, String formationTitle, Integer locationId, Date startDate, Date endDate, String formationTypeEnum) {
        this.formationId = formationId;
        this.formationTitle = formationTitle;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationTypeEnum;
    }

    public FormationDco(String formationTitle, Integer locationId, Date startDate, Date endDate, String formationTypeEnum) {
        this.formationTitle = formationTitle;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationTypeEnum;
    }

    public FormationDco() {
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

    public String getFormationType() {
        return formationType;
    }

    public void setFormationType(String formationTypeEnum) {
        this.formationType = formationTypeEnum;
    }
}
