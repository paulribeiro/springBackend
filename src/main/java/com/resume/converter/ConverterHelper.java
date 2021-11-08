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

    private static final ModelMapper modelMapper = new ModelMapper();

    //Experience
    public static ExperienceDco convertToDco(Experience experience) {
        return modelMapper.map(experience, ExperienceDco.class);
    }

    public static ExperienceDto convertToDto(Experience experience) {
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

    public static List<ExperienceDto> convertToExperienceListDto(List<Experience> experiences) {
        List<ExperienceDto> experienceDtoList = new ArrayList<>();
        for(Experience experience : experiences) {
            experienceDtoList.add(convertToDto(experience));
        }
        return experienceDtoList;
    }

    public static Experience convertToEntity(ExperienceDco experienceDco, LocationRepository locationRepository, OrganisationRepository organisationRepository) {
        Experience experience = modelMapper.map(experienceDco, Experience.class);
        experience.setLocation(locationRepository.findByLocationId(experienceDco.getLocationId()));
        experience.setOrganisation(organisationRepository.findByOrganisationId(experienceDco.getOrganisationId()));
        return experience;
    }

    //Competence
    public static CompetenceDco convertToDco(Competence competence) {
        return modelMapper.map(competence, CompetenceDco.class);
    }

    public static CompetenceDto convertToDto(Competence competence) {
        if(competence == null) {
            return null;
        }
        return modelMapper.map(competence, CompetenceDto.class);
    }

    public static List<CompetenceDto> convertToCompetenceListDto(List<Competence> competences) {
        List<CompetenceDto> competenceDtoList = new ArrayList<>();
        for(Competence competence : competences)
        {
            competenceDtoList.add(convertToDto(competence));
        }
        return competenceDtoList;
    }

    public static Competence convertToEntity(CompetenceDco competenceDco) {
        return modelMapper.map(competenceDco, Competence.class);
    }

    //Formation
    public static FormationDco convertToDco(Formation formation) {
        return modelMapper.map(formation, FormationDco.class);
    }

    public static FormationDto convertToDto(Formation formation) {
        FormationDto formationDto = modelMapper.map(formation, FormationDto.class);
        formationDto.setLocation(modelMapper.map(formation.getLocation(), LocationDto.class));
        formationDto.setFormationId(formation.getFormationId());
        return formationDto;
    }

    public static List<FormationDto> convertToFormationListDto(List<Formation> formations) {
        List<FormationDto> formationDtoList = new ArrayList<>();
        for(Formation formation : formations)
        {
            formationDtoList.add(convertToDto(formation));
        }
        return formationDtoList;
    }

    public static Formation convertToEntity(FormationDco formationDco, Location location) {
        Formation formation = modelMapper.map(formationDco, Formation.class);
        formation.setLocation(location);
        return formation;
    }

    //Location
    public static LocationDco convertToDco(Location location) {
        return modelMapper.map(location, LocationDco.class);
    }

    public static LocationDto convertToDto(Location location) {
        return modelMapper.map(location, LocationDto.class);
    }

    public static List<LocationDto> convertToLocationListDto(List<Location> locations) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for(Location location : locations)
        {
            locationDtoList.add(convertToDto(location));
        }
        return locationDtoList;
    }

    public static Location convertToEntity(LocationDco locationDco) {
        return modelMapper.map(locationDco, Location.class);
    }

    //Organisation
    public static OrganisationDco convertToDco(Organisation organisation) {
        return modelMapper.map(organisation, OrganisationDco.class);
    }

    public static OrganisationDto convertToDto(Organisation organisation) {
        return modelMapper.map(organisation, OrganisationDto.class);
    }

    public static List<OrganisationDto> convertToOrganisationListDto(List<Organisation> organisations) {
        List<OrganisationDto> organisationDtoList = new ArrayList<>();
        for(Organisation organisation : organisations)
        {
            organisationDtoList.add(convertToDto(organisation));
        }
        return organisationDtoList;
    }

    public static Organisation convertToEntity(OrganisationDco organisationDco, String organisationLogoName) {
        return new Organisation(organisationDco.getOrganisationId(), organisationDco.getOrganisationName(),
                organisationLogoName);
    }

    //Project
    public static ProjectDco convertToDco(Project project) {
        ProjectDco projectDco = modelMapper.map(project, ProjectDco.class);
        Set<Integer> competencesIdForProject = project.getCompetencesForProject()
                                                .stream().map(Competence::getCompetenceId).collect(Collectors.toSet());
        projectDco.setCompetenceIds(competencesIdForProject);
        return projectDco;
    }

    public static ProjectDto convertToDto(Project project) {
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

    public static List<ProjectDto> convertToProjectListDto(List<Project> projects) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project : projects) {
            projectDtoList.add(convertToDto(project));
        }
        return projectDtoList;
    }

    public static Project convertToEntity(ProjectDco projectDco, ExperienceRepository experienceRepository, CompetenceRepository competenceRepository) {
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
