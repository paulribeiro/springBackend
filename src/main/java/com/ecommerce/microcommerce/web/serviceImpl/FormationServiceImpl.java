package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.IFormationService;
import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.FormationDto;
import com.ecommerce.microcommerce.model.Formation;
import com.ecommerce.microcommerce.repository.FormationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationServiceImpl implements IFormationService {

    @Autowired
    private FormationRepository formationRepository;

    private ModelMapper modelMapper;

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
}
