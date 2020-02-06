package com.ecommerce.microcommerce.dto;

import com.ecommerce.microcommerce.model.enums.FormationType;

import java.util.Date;

public class FormationDco {

    private Integer formationId;

    private String formationTitle;

    private Integer locationId;

    private Date startDate;

    private Date endDate;

    private FormationType formationType;

    public FormationDco(Integer formationId, String formationTitle, Integer locationId, Date startDate, Date endDate, FormationType formationType) {
        this.formationId = formationId;
        this.formationTitle = formationTitle;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationType;
    }

    public FormationDco(String formationTitle, Integer locationId, Date startDate, Date endDate, FormationType formationType) {
        this.formationTitle = formationTitle;
        this.locationId = locationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formationType = formationType;
    }

    public FormationDco() {
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

    public FormationType getFormationType() {
        return formationType;
    }

    public void setFormationType(FormationType formationType) {
        this.formationType = formationType;
    }
}
