package com.resume.dco;

public class OrganisationDco {

    private Integer organisationId;

    private String organisationName;

    private String logoAddress;

    public OrganisationDco(Integer organisationId, String organisationName, String logoAddress) {
        this.organisationId = organisationId;
        this.organisationName = organisationName;
        this.logoAddress = logoAddress;
    }

    public OrganisationDco(String organisationName, String logoAddress) {
        this.organisationName = organisationName;
        this.logoAddress = logoAddress;
    }

    public OrganisationDco() {
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public String getLogoAddress() {
        return logoAddress;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public void setLogoAddress(String logoAddress) {
        this.logoAddress = logoAddress;
    }
}
