package com.resume.web.serviceImpl;

import com.resume.Services.ICompetenceService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.CompetenceDto;
import com.resume.model.Competence;
import com.resume.model.enums.CompetenceTypeEnum;
import com.resume.repository.CompetenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompetenceServiceImpl implements ICompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    private ModelMapper modelMapper;

    public CompetenceServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompetenceDto> getAllCompetences() {
        return ConverterHelper.convertToCompetenceListDto(competenceRepository.findAll(), modelMapper);
    }

    @Override
    public List<CompetenceDto> getCompetencesByType(CompetenceTypeEnum competenceType) {
        return ConverterHelper.convertToCompetenceListDto(competenceRepository.findAllByCompetenceTypeEnum(competenceType), modelMapper);
    }


    @Override
    public CompetenceDto postCompetence(Competence competence) {
        return ConverterHelper.convertToDto(competenceRepository.save(competence), modelMapper);
    }

    @Override
    public CompetenceDto putCompetence(Competence competence) {
        return ConverterHelper.convertToDto(competenceRepository.save(competence), modelMapper);
    }

    @Override
    public CompetenceDto getCompetence(Integer competenceId) {
        Competence competence = competenceRepository.findByCompetenceId(competenceId);
        if(competence == null) {
            return null;
        }
        return ConverterHelper.convertToDto(competence, modelMapper);
    }

    @Override
    public Integer deleteCompetence(Integer competenceId) {
        return competenceRepository.deleteCompetenceByCompetenceId(competenceId);
    }
}
