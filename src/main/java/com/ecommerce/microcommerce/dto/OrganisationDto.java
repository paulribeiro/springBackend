package com.ecommerce.microcommerce.dto;

public class OrganisationDto {

    private Integer organisationId;

    private String organisationName;

    private String logoAddress;

    public OrganisationDto() {
    }

    public OrganisationDto(String organisationName, String logoAddress) {
        this.organisationName = organisationName;
        this.logoAddress = logoAddress;
    }

    public OrganisationDto(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getLogoAddress() {
        return logoAddress;
    }

    public void setLogoAddress(String logoAddress) {
        this.logoAddress = logoAddress;
    }

    @Override
    public String toString(){
        return "Organisation{"+
                "id=" + organisationId +
                ", organisationName='"+ organisationName +
                ", logoAddress=" + logoAddress+ '}';
    }

}
