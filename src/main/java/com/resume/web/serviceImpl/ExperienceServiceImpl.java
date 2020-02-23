package com.resume.web.serviceImpl;

import com.resume.Services.IExperienceService;
import com.resume.converter.ConverterHelper;
import com.resume.dto.ExperienceDto;
import com.resume.model.Experience;
import com.resume.repository.ExperienceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    private ModelMapper modelMapper;

    public ExperienceServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExperienceDto> getAllExperiences() {
        return ConverterHelper.convertToExperienceListDto(experienceRepository.findAll(), modelMapper);
    }

    @Override
    public ExperienceDto postExperience(Experience experience) {
        return ConverterHelper.convertToDto(experienceRepository.save(experience), modelMapper);
    }

    @Override
    public ExperienceDto putExperience(Experience experience) {
        return ConverterHelper.convertToDto(experienceRepository.save(experience), modelMapper);
    }

    @Override
    public ExperienceDto getExperience(Integer experienceId) {
        return ConverterHelper.convertToDto(experienceRepository.findByExperienceId(experienceId), modelMapper);
    }

}
