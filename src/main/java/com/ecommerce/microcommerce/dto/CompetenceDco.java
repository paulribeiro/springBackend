package com.ecommerce.microcommerce.dto;

import com.ecommerce.microcommerce.model.enums.CompetenceType;

public class CompetenceDco {

    private Integer competenceId;

    private Integer evaluation;

    private String competenceTitle;

    private CompetenceType competenceType;

    public CompetenceDco(Integer competenceId, Integer evaluation, String competenceTitle, CompetenceType competenceType) {
        this.competenceId = competenceId;
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceType = competenceType;
    }

    public CompetenceDco(Integer evaluation, String competenceTitle, CompetenceType competenceType) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceType = competenceType;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public CompetenceDco() {
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
}
