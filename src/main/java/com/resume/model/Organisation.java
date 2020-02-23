package com.resume.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "RES_ORGANISATION")
public class Organisation {

    @Id
    @GeneratedValue
    private Integer organisationId;

    @Column(nullable = false)
    @Length(min=1, message = "le nom de l'organisation doit comporter au moins un caractère.")
    private String organisationName;

    @Length(min=1, message = "L'adresse du logo doit comporter au moins 1 caractère.")
    @Length(max=2000)
    private String logoAddress;

    //constructeur par défaut
    public Organisation() {
    }

    public Organisation(String organisationName, String logoAddress) {
        this.organisationName = organisationName;
        this.logoAddress = logoAddress;
    }

    public Organisation(Integer organisationId) {
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
