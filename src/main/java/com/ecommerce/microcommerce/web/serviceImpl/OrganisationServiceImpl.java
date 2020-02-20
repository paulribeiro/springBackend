package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.IOrganisationService;
import com.ecommerce.microcommerce.converter.ConverterHelper;
import com.ecommerce.microcommerce.dto.OrganisationDto;
import com.ecommerce.microcommerce.model.Organisation;
import com.ecommerce.microcommerce.repository.OrganisationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
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
        return ConverterHelper.convertToDto(organisationRepository.findByOrganisationId(organisationId), modelMapper);
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
