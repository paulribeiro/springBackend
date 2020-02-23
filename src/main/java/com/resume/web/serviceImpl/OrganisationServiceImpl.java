package com.resume.web.serviceImpl;

import com.resume.Services.IOrganisationService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.OrganisationDto;
import com.resume.model.Organisation;
import com.resume.repository.OrganisationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements IOrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    private ModelMapper modelMapper;

    public OrganisationServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrganisationDto> getAllOrganisation() {
        return ConverterHelper.convertToOrganisatonListDto(organisationRepository.findAll(), modelMapper);
    }

    @Override
    public OrganisationDto getOrganisationbyId(Integer organisationId) {
        Organisation organisation = organisationRepository.findByOrganisationId(organisationId);
        if(organisation == null) {
            return null;
        }
        return ConverterHelper.convertToDto(organisation, modelMapper);
    }

    @Override
    public OrganisationDto postOrganisation(Organisation organisation) {
        return ConverterHelper.convertToDto(organisationRepository.save(organisation), modelMapper);
    }

    @Override
    public OrganisationDto putOrganisation(Organisation organisation) {
        return ConverterHelper.convertToDto(organisationRepository.save(organisation), modelMapper);
    }
}
