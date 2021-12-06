package com.resume.model.organisation;

public class OrganisationDco {

    private Integer organisationId;

    private String organisationName;

    public OrganisationDco(Integer organisationId, String organisationName) {
        this.organisationId = organisationId;
        this.organisationName = organisationName;
    }

    public OrganisationDco(String organisationName) {
        this.organisationName = organisationName;
    }

    public OrganisationDco() {
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }
}
