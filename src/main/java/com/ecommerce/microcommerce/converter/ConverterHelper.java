package com.ecommerce.microcommerce.converter;

import com.ecommerce.microcommerce.dto.CompetenceDco;
import com.ecommerce.microcommerce.dto.ExperienceDco;
import com.ecommerce.microcommerce.model.Competence;
import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.web.serviceImpl.LocationServiceImpl;
import com.ecommerce.microcommerce.web.serviceImpl.OrganisationServiceImpl;
import org.modelmapper.ModelMapper;

public class ConverterHelper {

    public ExperienceDco convertToDto(Experience experience, ModelMapper modelMapper) {
        return modelMapper.map(experience, ExperienceDco.class);
    }

    public Experience convertToEntity(ExperienceDco experienceDco, ModelMapper modelMapper, LocationServiceImpl locationServiceImpl, OrganisationServiceImpl organisationServiceImpl) {
        Experience experience = modelMapper.map(experienceDco, Experience.class);
        experience.setLocation(locationServiceImpl.getLocationbyId(experienceDco.getLocationId()));
        experience.setOrganisation(organisationServiceImpl.getOrganisationbyId(experienceDco.getOrganisationId()));
        return experience;
    }

    public CompetenceDco convertToDto(Competence competence, ModelMapper modelMapper) {
        return modelMapper.map(competence, CompetenceDco.class);
    }

    public Competence convertToEntity(CompetenceDco competenceDco, ModelMapper modelMapper) {
        return modelMapper.map(competenceDco, Competence.class);
    }

}
