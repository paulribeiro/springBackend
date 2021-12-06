package com.resume.web.serviceImpl;

import com.resume.Services.IProjectService;
import com.resume.converter.ConverterHelper;
import com.resume.model.project.ProjectDco;
import com.resume.model.project.ProjectDto;
import com.resume.model.competence.Competence;
import com.resume.model.experience.Experience;
import com.resume.model.project.Project;
import com.resume.model.enums.ProjectTypeEnum;
import com.resume.repository.CompetenceRepository;
import com.resume.repository.ExperienceRepository;
import com.resume.repository.ProjectRepository;
import com.resume.web.exceptions.UnexpectedElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;

    private final ExperienceRepository experienceRepository;

    private final CompetenceRepository competenceRepository;

    public static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    public ProjectServiceImpl(ProjectRepository projectRepository, ExperienceRepository experienceRepository,
                              CompetenceRepository competenceRepository) {
        this.projectRepository = projectRepository;
        this.experienceRepository = experienceRepository;
        this.competenceRepository = competenceRepository;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return ConverterHelper.convertToProjectListDto(projectRepository.findAll());
    }

    @Override
    public ProjectDto postProject(ProjectDco project) {
        Set<Competence> competences = new HashSet<>();
        for(Integer competenceId: project.getCompetenceIds()) {
            Competence competence = competenceRepository.findByCompetenceId(competenceId);
            if(competence != null) {
                competences.add(competence);
            } else {
                logger.error("Unable to create project with id {} due to unknown competence. Competence with id {} not found.", project.getProjectId(), competenceId);
                throw new UnexpectedElementException("Unable to create Project due to missing Competence. Competence with id " + competenceId + " not found.");
            }
        }

        Experience experience = null;
        if(project.getExperienceId() != null) {
            experience = experienceRepository.findByExperienceId(project.getExperienceId());
        }

        return ConverterHelper.convertToDto(projectRepository.save(ConverterHelper.convertToEntity(project, experience, competences)));
    }

    @Override
    public ProjectDto putProject(ProjectDco project) {

        Project currentProject = projectRepository.findByProjectId(project.getProjectId());
        currentProject.setDescription(project.getDescription());
        currentProject.setLinkToProject(project.getLinkToProject());
        currentProject.setProjectType(ProjectTypeEnum.valueOf(project.getProjectType()));
        currentProject.setTitle(project.getTitle());

        if(project.getExperienceId() != null) {
            currentProject.setExperience(experienceRepository.findByExperienceId(project.getExperienceId()));
        }
        if(project.getCompetenceIds() != null) {
            Set<Competence> competencesForProject = project.getCompetenceIds().stream().map(competenceRepository::findByCompetenceId).collect(Collectors.toSet());
            currentProject.setCompetencesForProject(competencesForProject);
        }

        return ConverterHelper.convertToDto(projectRepository.save(currentProject));
    }

    @Override
    public ProjectDto getProject(Integer projectId) {
        return ConverterHelper.convertToDto(projectRepository.findByProjectId(projectId));
    }

    @Override
    public Integer deleteProject(Integer projectId) {
        return projectRepository.deleteProjectByProjectId(projectId);
    }

    @Override
    public List<ProjectDto> getProjectLinkedToExperience(Integer experienceId) {
        return ConverterHelper.convertToProjectListDto(projectRepository.findByExperience_ExperienceId(experienceId));
    }
}
