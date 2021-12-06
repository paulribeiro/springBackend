package com.resume.model.competence;

import com.resume.model.enums.CompetenceEvaluationEnum;
import com.resume.model.enums.CompetenceTypeEnum;

public class CompetenceDco {

    private Integer competenceId;

    private CompetenceEvaluationEnum evaluation;

    private String competenceTitle;

    private String competenceDescription;

    private CompetenceTypeEnum competenceTypeEnum;

    public CompetenceDco(Integer competenceId, CompetenceEvaluationEnum evaluation, String competenceTitle, String competenceDescription, CompetenceTypeEnum competenceTypeEnum) {
        this.competenceId = competenceId;
        this.evaluation = evaluation;
        this.competenceDescription = competenceDescription;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public CompetenceDco(CompetenceEvaluationEnum evaluation, String competenceTitle, String competenceDescription, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceDescription = competenceDescription;
        this.competenceTypeEnum = competenceTypeEnum;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public CompetenceDco() {
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public CompetenceEvaluationEnum getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(CompetenceEvaluationEnum evaluation) {
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

    public String getCompetenceDescription() {
        return competenceDescription;
    }

    public void setCompetenceDescription(String competenceDescription) {
        this.competenceDescription = competenceDescription;
    }
}