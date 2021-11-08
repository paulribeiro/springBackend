package com.resume.web.serviceImpl;

import com.resume.Services.IExperienceService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.ExperienceDto;
import com.resume.model.Experience;
import com.resume.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return ConverterHelper.convertToExperienceListDto(experienceRepository.findAll());
    }

    @Override
    public ExperienceDto postExperience(Experience experience) {
        return ConverterHelper.convertToDto(experienceRepository.save(experience));
    }

    @Override
    public ExperienceDto putExperience(Experience experience) {
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
}
