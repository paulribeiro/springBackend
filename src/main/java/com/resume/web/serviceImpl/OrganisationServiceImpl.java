package com.resume.web.serviceImpl;

import com.resume.Services.IOrganisationService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.OrganisationDto;
import com.resume.model.Organisation;
import com.resume.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrganisationServiceImpl implements IOrganisationService {

    private final OrganisationRepository organisationRepository;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public List<OrganisationDto> getAllOrganisation() {
        return ConverterHelper.convertToOrganisationListDto(organisationRepository.findAll());
    }

    @Override
    public OrganisationDto getOrganisationbyId(Integer organisationId) {
        Organisation organisation = organisationRepository.findByOrganisationId(organisationId);
        if(organisation == null) {
            return null;
        }
        return ConverterHelper.convertToDto(organisation);
    }

    @Override
    public OrganisationDto postOrganisation(Organisation organisation) {
        return ConverterHelper.convertToDto(organisationRepository.save(organisation));
    }

    @Override
    public OrganisationDto putOrganisation(Organisation organisation) {
        return ConverterHelper.convertToDto(organisationRepository.save(organisation));
    }

    @Override
    public Integer deleteOrganisation(Integer organisationId) {
        return organisationRepository.deleteOrganisationByOrganisationId(organisationId);
    }
}
