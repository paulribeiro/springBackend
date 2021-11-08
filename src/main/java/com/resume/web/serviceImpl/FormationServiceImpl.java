package com.resume.web.serviceImpl;

import com.resume.Services.IFormationService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.FormationDto;
import com.resume.model.Formation;
import com.resume.repository.FormationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FormationServiceImpl implements IFormationService {

    @Autowired
    private FormationRepository formationRepository;

    private final ModelMapper modelMapper;

    public FormationServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FormationDto> getAllFormations() {
        return ConverterHelper.convertToFormationListDto(formationRepository.findAll(), modelMapper);
    }

    @Override
    public FormationDto postFormation(Formation formation) {
        return ConverterHelper.convertToDto(formationRepository.save(formation), modelMapper);
    }

    @Override
    public FormationDto putFormation(Formation formation) {
        return ConverterHelper.convertToDto(formationRepository.save(formation), modelMapper);
    }

    @Override
    public FormationDto getFormation(Integer formationId) {
        Formation formation = formationRepository.findByFormationId(formationId);
        if(formation == null) {
            return null;
        }
        return ConverterHelper.convertToDto(formation, modelMapper);
    }

    @Override
    public Integer deleteFormation(Integer formationId) {
        return formationRepository.deleteFormationByFormationId(formationId);
    }

    @Override
    public List<FormationDto> getFormationLinkedToLocation(Integer locationId) {
        return ConverterHelper.convertToFormationListDto(formationRepository.findByLocation_LocationId(locationId), modelMapper);
    }
}
