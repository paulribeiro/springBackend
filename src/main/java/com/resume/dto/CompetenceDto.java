package com.resume.dto;

import com.resume.model.enums.CompetenceEvaluationEnum;
import com.resume.model.enums.CompetenceTypeEnum;

public class CompetenceDto {

    private Integer competenceId;

    private CompetenceEvaluationEnum evaluation;

    private String competenceTitle;

    private String competenceDescription;

    private CompetenceTypeEnum competenceTypeEnum;

    public CompetenceDto() {
    }

    public CompetenceDto(CompetenceEvaluationEnum evaluation, String competenceTitle, String competenceDescription, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
        this.competenceDescription = competenceDescription;
    }

    public Integer getCompetenceId() {
        return competenceId;
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

    @Override
    public String toString(){
        return "Competence{"+
                "competenceId=" + competenceId +
                ", evaluation='"+ evaluation +
                ", competenceTitle='"+ competenceTitle +
                ", competenceDescription='"+ competenceDescription +
                ", competenceType='"+ competenceTypeEnum + '}';
    }

}
