package com.ecommerce.microcommerce.model;

import com.ecommerce.microcommerce.model.enums.CompetenceType;

import javax.persistence.*;

@Entity
@Table(name = "RES_COMPETENCE")
public class Competence {

    @Id
    @GeneratedValue
    private Integer competenceId;

    private Integer evaluation;

    @Column(nullable = false)
    private String competenceTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetenceType competenceType;

    public Competence() {
    }

    public Competence(Integer evaluation, String competenceTitle, CompetenceType competenceType) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceType = competenceType;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getCompetenceTitle() {
        return competenceTitle;
    }

    public void setCompetenceTitle(String competenceTitle) {
        this.competenceTitle = competenceTitle;
    }

    public CompetenceType getCompetenceType() {
        return competenceType;
    }

    public void setCompetenceType(CompetenceType competenceType) {
        this.competenceType = competenceType;
    }

    @Override
    public String toString(){
        return "Competence{"+
                "competenceId=" + competenceId +
                ", evaluation='"+ evaluation +
                ", competenceTitle='"+ competenceTitle +
                ", competenceType='"+ competenceType + '}';
    }

}
