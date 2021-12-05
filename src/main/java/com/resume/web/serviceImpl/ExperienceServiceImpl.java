package com.resume.web.serviceImpl;

import com.resume.Services.IExperienceService;
import com.resume.converter.ConverterHelper;
import com.resume.dco.ExperienceDco;
import com.resume.dto.ExperienceDto;
import com.resume.model.Experience;
import com.resume.model.Location;
import com.resume.model.Organisation;
import com.resume.model.enums.ExperienceTypeEnum;
import com.resume.repository.ExperienceRepository;
import com.resume.repository.LocationRepository;
import com.resume.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExperienceServiceImpl implements IExperienceService {

    private final ExperienceRepository experienceRepository;

    private final LocationRepository locationRepository;

    private final OrganisationRepository organisationRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, LocationRepository locationRepository, OrganisationRepository organisationRepository) {
        this.experienceRepository = experienceRepository;
        this.locationRepository = locationRepository;
        this.organisationRepository = organisationRepository;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return ConverterHelper.convertToExperienceListDto(experienceRepository.findAll());
    }

    @Override
    public ExperienceDto postExperience(ExperienceDco experienceDco) {
        Location location = locationRepository.findByLocationId(experienceDco.getLocationId());
        Organisation organisation = organisationRepository.findByOrganisationId(experienceDco.getOrganisationId());

        Experience experience = ConverterHelper.convertToEntity(experienceDco, location, organisation);

        return ConverterHelper.convertToDto(experienceRepository.save(experience));
    }

    @Override
    public ExperienceDto putExperience(ExperienceDco experienceDco) {

        Experience experience = experienceRepository.findByExperienceId(experienceDco.getExperienceId());
        if(experience == null) {
            return null;
        }

        experience.setDescription(experienceDco.getDescription());
        experience.setEndDate(experienceDco.getEndDate());
        experience.setStartDate(experienceDco.getStartDate());
        experience.setExperienceType(ExperienceTypeEnum.valueOf(experienceDco.getExperienceType()));
        experience.setOrganisation(organisationRepository.findByOrganisationId(experienceDco.getOrganisationId()));
        experience.setLocation(locationRepository.findByLocationId(experienceDco.getLocationId()));
        experience.setTitle(experienceDco.getTitle());

        return ConverterHelper.convertToDto(experienceRepository.save(experience));
    }

    @Override
    public ExperienceDto getExperience(Integer experienceId) {
        return ConverterHelper.convertToDto(experienceRepository.findByExperienceId(experienceId));
    }

    @Override
    public List<ExperienceDto> getExperienceLinkedToLocation(Integer locationId) {
        return ConverterHelper.convertToExperienceListDto(experienceRepository.findByLocation_LocationId(locationId));
    }

    @Override
    public List<ExperienceDto> getExperienceLinkedToOrganisation(Integer organisationId) {
        return ConverterHelper.convertToExperienceListDto(experienceRepository.findByOrganisation_OrganisationId(organisationId));
    }

    @Override
    public Integer deleteExperience(Integer experienceId) {
        return experienceRepository.deleteExperienceByExperienceId(experienceId);
    }
}
