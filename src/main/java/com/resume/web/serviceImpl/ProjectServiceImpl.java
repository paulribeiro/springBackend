package com.resume.web.serviceImpl;

import com.resume.Services.IExperienceService;
import com.resume.Services.IProjectService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.ExperienceDto;
import com.resume.dto.ProjectDto;
import com.resume.model.Experience;
import com.resume.model.Project;
import com.resume.repository.ExperienceRepository;
import com.resume.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private ModelMapper modelMapper;

    public ProjectServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return ConverterHelper.convertToProjectListDto(projectRepository.findAll(), modelMapper);
    }

    @Override
    public ProjectDto postProject(Project project) {
        return ConverterHelper.convertToDto(projectRepository.save(project), modelMapper);
    }

    @Override
    public ProjectDto putProject(Project project) {
        return ConverterHelper.convertToDto(projectRepository.save(project), modelMapper);
    }

    @Override
    public ProjectDto getProject(Integer projectId) {
        return ConverterHelper.convertToDto(projectRepository.findByProjectId(projectId), modelMapper);
    }

}
