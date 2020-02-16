package com.ecommerce.microcommerce.dto;

import com.ecommerce.microcommerce.model.enums.CompetenceTypeEnum;

public class CompetenceDco {

    private Integer competenceId;

    private Integer evaluation;

    private String competenceTitle;


    private CompetenceTypeEnum competenceTypeEnum;

    public CompetenceDco(Integer competenceId, Integer evaluation, String competenceTitle, CompetenceTypeEnum competenceTypeEnum) {
        this.competenceId = competenceId;
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public CompetenceDco(Integer evaluation, String competenceTitle, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
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

    public CompetenceTypeEnum getCompetenceTypeEnum() {
        return competenceTypeEnum;
    }

    public void setCompetenceTypeEnum(CompetenceTypeEnum competenceTypeEnum) {
        this.competenceTypeEnum = competenceTypeEnum;
    }
}
