package com.resume.business.competence;

import com.resume.controller.Services.ICompetenceService;
import com.resume.converter.ConverterHelper;
import com.resume.model.competence.Competence;
import com.resume.model.competence.CompetenceDto;
import com.resume.model.enums.CompetenceTypeEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompetenceServiceImpl implements ICompetenceService {

    private final CompetenceRepository competenceRepository;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    public List<CompetenceDto> getAllCompetences() {
        return ConverterHelper.convertToCompetenceListDto(competenceRepository.findAll());
    }

    @Override
    public List<CompetenceDto> getCompetencesByType(CompetenceTypeEnum competenceType) {
        return ConverterHelper.convertToCompetenceListDto(competenceRepository.findAllByCompetenceTypeEnum(competenceType));
    }


    @Override
    public CompetenceDto postCompetence(Competence competence) {
        return ConverterHelper.convertToDto(competenceRepository.save(competence));
    }

    @Override
    public CompetenceDto putCompetence(CompetenceDto competenceDto) {

        Competence competence = competenceRepository.findByCompetenceId(competenceDto.getCompetenceId());
        if(competence == null) {
            return null;
        }
        competence.setCompetenceTitle(competenceDto.getCompetenceTitle());
        competence.setCompetenceDescription(competenceDto.getCompetenceDescription());
        competence.setCompetenceTypeEnum(competenceDto.getCompetenceTypeEnum());
        competence.setEvaluation(competenceDto.getEvaluation());

        return ConverterHelper.convertToDto(competenceRepository.save(competence));
    }

    @Override
    public CompetenceDto getCompetence(Integer competenceId) {
        Competence competence = competenceRepository.findByCompetenceId(competenceId);
        if(competence == null) {
            return null;
        }
        return ConverterHelper.convertToDto(competence);
    }

    @Override
    public Integer deleteCompetence(Integer competenceId) {
        return competenceRepository.deleteCompetenceByCompetenceId(competenceId);
    }
}
