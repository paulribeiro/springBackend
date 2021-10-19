package com.resume.converter;

import com.resume.dco.*;
import com.resume.dto.*;
import com.resume.model.*;
import com.resume.repository.CompetenceRepository;
import com.resume.repository.ExperienceRepository;
import com.resume.repository.LocationRepository;
import com.resume.repository.OrganisationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConverterHelper {

    //Experience
    public static ExperienceDco convertToDco(Experience experience, ModelMapper modelMapper) {
        return modelMapper.map(experience, ExperienceDco.class);
    }

    public static ExperienceDto convertToDto(Experience experience, ModelMapper modelMapper) {
        if(experience == null) {
            return null;
        }

        ExperienceDto experienceDto = modelMapper.map(experience, ExperienceDto.class);
        if(experience.getLocation() != null) {
            experienceDto.setLocationDto(modelMapper.map(experience.getLocation(), LocationDto.class));
        }
        if(experience.getOrganisation() != null) {
            experienceDto.setOrganisationDto(modelMapper.map(experience.getOrganisation(), OrganisationDto.class));
        }
        return experienceDto;
    }

    public static List<ExperienceDto> convertToExperienceListDto(List<Experience> experiences, ModelMapper modelMapper) {
        List<ExperienceDto> experienceDtoList = new ArrayList<>();
        for(Experience experience : experiences) {
            experienceDtoList.add(convertToDto(experience, modelMapper));
        }
        return experienceDtoList;
    }

    public static Experience convertToEntity(ExperienceDco experienceDco, ModelMapper modelMapper, LocationRepository locationRepository, OrganisationRepository organisationRepository) {
        Experience experience = modelMapper.map(experienceDco, Experience.class);
        experience.setLocation(locationRepository.findByLocationId(experienceDco.getLocationId()));
        experience.setOrganisation(organisationRepository.findByOrganisationId(experienceDco.getOrganisationId()));
        return experience;
    }

    //Competence
    public static CompetenceDco convertToDco(Competence competence, ModelMapper modelMapper) {
        return modelMapper.map(competence, CompetenceDco.class);
    }

    public static CompetenceDto convertToDto(Competence competence, ModelMapper modelMapper) {
        if(competence == null) {
            return null;
        }
        return modelMapper.map(competence, CompetenceDto.class);
    }

    public static List<CompetenceDto> convertToCompetenceListDto(List<Competence> competences, ModelMapper modelMapper) {
        List<CompetenceDto> competenceDtoList = new ArrayList<>();
        for(Competence competence : competences)
        {
            competenceDtoList.add(convertToDto(competence, modelMapper));
        }
        return competenceDtoList;
    }

    public static Competence convertToEntity(CompetenceDco competenceDco, ModelMapper modelMapper) {
        return modelMapper.map(competenceDco, Competence.class);
    }

    //Formation
    public static FormationDco convertToDco(Formation formation, ModelMapper modelMapper) {
        return modelMapper.map(formation, FormationDco.class);
    }

    public static FormationDto convertToDto(Formation formation, ModelMapper modelMapper) {
        FormationDto formationDto = modelMapper.map(formation, FormationDto.class);
        formationDto.setLocation(modelMapper.map(formation.getLocation(), LocationDto.class));
        formationDto.setFormationId(formation.getFormationId());
        return formationDto;
    }

    public static List<FormationDto> convertToFormationListDto(List<Formation> formations, ModelMapper modelMapper) {
        List<FormationDto> formationDtoList = new ArrayList<>();
        for(Formation formation : formations)
        {
            formationDtoList.add(convertToDto(formation, modelMapper));
        }
        return formationDtoList;
    }

    public static Formation convertToEntity(FormationDco formationDco, ModelMapper modelMapper, LocationRepository locationRepository) {
        Formation formation = modelMapper.map(formationDco, Formation.class);
        formation.setLocation(locationRepository.findByLocationId(formationDco.getLocationId()));
        return formation;
    }

    //Location
    public static LocationDco convertToDco(Location location, ModelMapper modelMapper) {
        return modelMapper.map(location, LocationDco.class);
    }

    public static LocationDto convertToDto(Location location, ModelMapper modelMapper) {
        return modelMapper.map(location, LocationDto.class);
    }

    public static List<LocationDto> convertToLocationListDto(List<Location> locations, ModelMapper modelMapper) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for(Location location : locations)
        {
            locationDtoList.add(convertToDto(location, modelMapper));
        }
        return locationDtoList;
    }

    public static Location convertToEntity(LocationDco locationDco, ModelMapper modelMapper) {
        return modelMapper.map(locationDco, Location.class);
    }

    //Organisation
    public static OrganisationDco convertToDco(Organisation organisation, ModelMapper modelMapper) {
        return modelMapper.map(organisation, OrganisationDco.class);
    }

    public static OrganisationDto convertToDto(Organisation organisation, ModelMapper modelMapper) {
        return modelMapper.map(organisation, OrganisationDto.class);
    }

    public static List<OrganisationDto> convertToOrganisatonListDto(List<Organisation> organisations, ModelMapper modelMapper) {
        List<OrganisationDto> organisationDtoList = new ArrayList<>();
        for(Organisation organisation : organisations)
        {
            organisationDtoList.add(convertToDto(organisation, modelMapper));
        }
        return organisationDtoList;
    }

    public static Organisation convertToEntity(OrganisationDco organisationDco, ModelMapper modelMapper) {
        return modelMapper.map(organisationDco, Organisation.class);
    }

    //Project
    public static ProjectDco convertToDco(Project project, ModelMapper modelMapper) {
        ProjectDco projectDco = modelMapper.map(project, ProjectDco.class);
        Set<Integer> competencesIdForProject = project.getCompetencesForProject()
                                                .stream().map(Competence::getCompetenceId).collect(Collectors.toSet());
        projectDco.setCompetenceIds(competencesIdForProject);
        return projectDco;
    }

    public static ProjectDto convertToDto(Project project, ModelMapper modelMapper) {
        if(project == null) {
            return null;
        }
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        if(project.getExperience() != null) {
            projectDto.setExperience(modelMapper.map(project.getExperience(), ExperienceDto.class));
        }
        if(project.getCompetencesForProject() == null) {
            projectDto.setCompetences(Collections.emptySet());
        }
        return projectDto;
    }

    public static List<ProjectDto> convertToProjectListDto(List<Project> projects, ModelMapper modelMapper) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project : projects) {
            projectDtoList.add(convertToDto(project, modelMapper));
        }
        return projectDtoList;
    }

    public static Project convertToEntity(ProjectDco projectDco, ModelMapper modelMapper, ExperienceRepository experienceRepository, CompetenceRepository competenceRepository) {
        Project project = modelMapper.map(projectDco, Project.class);
        if(projectDco.getExperienceId() != null) {
            Experience experienceAttachedToProject = experienceRepository.findByExperienceId(projectDco.getExperienceId());
            if(experienceAttachedToProject != null) {
                project.setExperience(experienceAttachedToProject);
            }
        }
        if(!CollectionUtils.isEmpty(projectDco.getCompetenceIds())) {
            Set<Competence> competenceForProject = Collections.EMPTY_SET;
            try {
                competenceForProject = projectDco.getCompetenceIds().stream().map(id -> competenceRepository.findByCompetenceId(id)).collect(Collectors.toSet());
            }
            catch (Exception e) {
                return null;
            }
            project.setCompetencesForProject(competenceForProject);
        }
        return project;
    }
}
