package com.ecommerce.microcommerce.converter;

import com.ecommerce.microcommerce.dto.ExperienceDto;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.web.service.LocationService;
import com.ecommerce.microcommerce.web.service.OrganisationService;
import org.modelmapper.ModelMapper;

public class ConverterHelper {

    public ExperienceDto convertToDto(Experience experience, ModelMapper modelMapper) {
        return modelMapper.map(experience, ExperienceDto.class);
    }

    public Experience convertToEntity(ExperienceDto experienceDto, ModelMapper modelMapper, LocationService locationService, OrganisationService organisationService) {
        Experience experience = modelMapper.map(experienceDto, Experience.class);
        experience.setLocation(locationService.getLocationbyId(experienceDto.getLocationId()));
        experience.setOrganisation(organisationService.getOrganisationbyId(experienceDto.getOrganisationId()));
        return experience;
    }


}
