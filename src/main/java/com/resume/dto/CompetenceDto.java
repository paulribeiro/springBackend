package com.resume.dto;

import com.resume.model.enums.CompetenceTypeEnum;

public class CompetenceDto {

    private Integer competenceId;

    private Integer evaluation;

    private String competenceTitle;

    private CompetenceTypeEnum competenceTypeEnum;

    public CompetenceDto() {
    }

    public CompetenceDto(Integer evaluation, String competenceTitle, CompetenceTypeEnum competenceTypeEnum) {
        this.evaluation = evaluation;
        this.competenceTitle = competenceTitle;
        this.competenceTypeEnum = competenceTypeEnum;
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

    public CompetenceTypeEnum getCompetenceTypeEnum() {
        return competenceTypeEnum;
    }

    public void setCompetenceTypeEnum(CompetenceTypeEnum competenceTypeEnum) {
        this.competenceTypeEnum = competenceTypeEnum;
    }

    @Override
    public String toString(){
        return "Competence{"+
                "competenceId=" + competenceId +
                ", evaluation='"+ evaluation +
                ", competenceTitle='"+ competenceTitle +
                ", competenceType='"+ competenceTypeEnum + '}';
    }

}
