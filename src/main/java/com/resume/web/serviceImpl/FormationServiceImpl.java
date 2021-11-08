package com.resume.web.serviceImpl;

import com.resume.Services.IFormationService;
import com.resume.converter.ConverterHelper;
import com.resume.dco.FormationDco;
import com.resume.dto.FormationDto;
import com.resume.model.Formation;
import com.resume.model.Location;
import com.resume.model.enums.FormationTypeEnum;
import com.resume.repository.FormationRepository;
import com.resume.repository.LocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FormationServiceImpl implements IFormationService {

    private final FormationRepository formationRepository;

    private final LocationRepository locationRepository;

    public FormationServiceImpl(FormationRepository formationRepository, LocationRepository locationRepository) {
        this.formationRepository = formationRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<FormationDto> getAllFormations() {
        return ConverterHelper.convertToFormationListDto(formationRepository.findAll());
    }

    @Override
    public FormationDto postFormation(FormationDco formationDco) {

        Location location = locationRepository.findByLocationId(formationDco.getLocationId());

        Formation formation = ConverterHelper.convertToEntity(formationDco, location);

        return ConverterHelper.convertToDto(formationRepository.save(formation));
    }

    @Override
    public FormationDto putFormation(FormationDco formationDco) {

        Formation formation = formationRepository.findByFormationId(formationDco.getFormationId());
        if(formation == null) {
            return null;
        }
        formation.setStartDate(formationDco.getStartDate());
        formation.setEndDate(formationDco.getEndDate());
        formation.setFormationTitle(formationDco.getFormationTitle());
        formation.setFormationType(FormationTypeEnum.valueOf(formationDco.getFormationType()));
        formation.setLocation(locationRepository.findByLocationId(formationDco.getLocationId()));

        return ConverterHelper.convertToDto(formationRepository.save(formation));
    }

    @Override
    public FormationDto getFormation(Integer formationId) {
        Formation formation = formationRepository.findByFormationId(formationId);
        if(formation == null) {
            return null;
        }
        return ConverterHelper.convertToDto(formation);
    }

    @Override
    public Integer deleteFormation(Integer formationId) {
        return formationRepository.deleteFormationByFormationId(formationId);
    }

    @Override
    public List<FormationDto> getFormationLinkedToLocation(Integer locationId) {
        return ConverterHelper.convertToFormationListDto(formationRepository.findByLocation_LocationId(locationId));
    }
}
