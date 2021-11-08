package com.resume.web.serviceImpl;

import com.resume.Services.IProjectService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.ProjectDto;
import com.resume.model.Project;
import com.resume.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return ConverterHelper.convertToProjectListDto(projectRepository.findAll());
    }

    @Override
    public ProjectDto postProject(Project project) {
        return ConverterHelper.convertToDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto putProject(Project project) {
        return ConverterHelper.convertToDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto getProject(Integer projectId) {
        return ConverterHelper.convertToDto(projectRepository.findByProjectId(projectId));
    }

    @Override
    public Integer deleteProject(Integer projectId) {
        return projectRepository.deleteProjectByProjectId(projectId);
    }
}
