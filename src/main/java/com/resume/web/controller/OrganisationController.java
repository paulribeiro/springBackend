package com.resume.web.controller;

import com.resume.Services.IExperienceService;
import com.resume.Services.IOrganisationService;
import com.resume.dto.ExperienceDto;
import com.resume.dto.OrganisationDto;
import com.resume.model.Organisation;
import com.resume.web.exceptions.NoContentException;
import com.resume.web.exceptions.UnexpectedCompetenceException;
import com.resume.web.exceptions.UnexpectedOrganisationException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
public class OrganisationController {

    public static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);
    private static final String DIR_TO_UPLOAD = "./assets/organisations/";

    private final IOrganisationService organisationService;

    private final IExperienceService experienceService;

    public OrganisationController(IOrganisationService organisationService, IExperienceService experienceService) {
        this.organisationService = organisationService;
        this.experienceService = experienceService;
    }

    @CrossOrigin()
    @ApiOperation(value = "Get all the Organisations")
    @GetMapping(value = "/Organisations")
    public ResponseEntity<List<OrganisationDto>> get() {
        return ResponseEntity.ok().body(organisationService.getAllOrganisation());
    }

    @CrossOrigin()
    @ApiOperation(value = "Post an organisation")
    @PostMapping(value = "/Organisations")
    public ResponseEntity<OrganisationDto> post(@Valid @RequestParam String organisationName, @RequestParam MultipartFile image)  throws IOException {

        if(image != null) {
            byte[] bytes = image.getBytes();
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                Path path = Paths.get(DIR_TO_UPLOAD + fileName);
                Files.write(path, bytes, StandardOpenOption.CREATE_NEW);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new OrganisationDto(organisationName, fileName));
            }
            return ResponseEntity.ok().body(organisationService.postOrganisation(new Organisation(organisationName, fileName)));
        } else {
            return ResponseEntity.badRequest().body(new OrganisationDto(organisationName));
        }
    }

    @CrossOrigin()
    @ApiOperation(value = "Update an Organisation")
    @PutMapping(value = "/Organisations/{organisationId}")
    public ResponseEntity<OrganisationDto> put(@PathVariable Integer organisationId,
                                               @Valid @RequestParam String organisationName, @RequestParam MultipartFile image) throws IOException {

        logger.info("Updating organisation with id {}", organisationId);
        OrganisationDto currentOrganisation = organisationService.getOrganisationbyId(organisationId);

        if (currentOrganisation == null) {
            logger.error("Unable to update. Organisation with id {} not found.", organisationId);
            throw new UnexpectedOrganisationException("Unable to upate Organisation with id " + organisationId + " not found.");
        }
        currentOrganisation.setOrganisationName(organisationName);

        if(image != null) {
            byte[] bytes = image.getBytes();
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            try {
                Path path = Paths.get(DIR_TO_UPLOAD + fileName);
                Files.write(path, bytes, StandardOpenOption.CREATE_NEW);
                Path pathToDelete = Paths.get(DIR_TO_UPLOAD + currentOrganisation.getLogoAddress());
                Files.deleteIfExists(pathToDelete);
                currentOrganisation.setLogoAddress(fileName);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new OrganisationDto(organisationName, fileName));
            }
            return ResponseEntity.ok().body(organisationService.putOrganisation(currentOrganisation));
        } else {
            return ResponseEntity.badRequest().body(new OrganisationDto(organisationName));
        }
    }

    @CrossOrigin()
    @ApiOperation(value = "Get a given organisation")
    @GetMapping(value = "/Organisations/{organisationId}")
    public ResponseEntity<OrganisationDto> get(@PathVariable int organisationId) {

        logger.info("getting organisation with id {}", organisationId);
        OrganisationDto organisationDto = organisationService.getOrganisationbyId(organisationId);

        if(organisationDto == null) {
            logger.error("Unable to get organisation with id {} not found.", organisationId);
            throw new NoContentException("Unable to get location with id " + organisationId + " not found.");
        }

        return ResponseEntity.ok().body(organisationDto);
    }

    @CrossOrigin()
    @ApiOperation(value = "Get the picture of the organisation")
    @GetMapping(value = "/Organisations/picture/{organisationId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable int organisationId) throws IOException {

        logger.info("getting organisation with id {}", organisationId);
        OrganisationDto organisationDto = organisationService.getOrganisationbyId(organisationId);

        if(organisationDto == null) {
            logger.error("Unable to get organisation with id {} not found.", organisationId);
            throw new NoContentException("Unable to get location with id " + organisationId + " not found.");
        }

        String filename = organisationDto.getLogoAddress();
        logger.info("searching for picture: {}", filename);

        File serverFile = new File(DIR_TO_UPLOAD + filename);

        return ResponseEntity.ok().body(Files.readAllBytes(serverFile.toPath()));
    }

    @CrossOrigin()
    @ApiOperation(value = "Delete an organisation")
    @DeleteMapping(value = "/Organisations/{organisationId}")
    public ResponseEntity<OrganisationDto> delete(@PathVariable Integer organisationId) {
        logger.info("Deleting organisation with id {}", organisationId);
        OrganisationDto organisationToDelete = organisationService.getOrganisationbyId(organisationId);

        if(organisationToDelete == null) {
            logger.error("Unable to get organisation with id {} not found.", organisationId);
            throw new NoContentException("Unable to get location with id " + organisationId + " not found.");
        }

        List<ExperienceDto> experienceLinkedToOrganisation = experienceService.getExperienceLinkedToOrganisation(organisationToDelete.getOrganisationId());
        if (experienceLinkedToOrganisation != null && !experienceLinkedToOrganisation.isEmpty())  {
            String dependantExperience = experienceLinkedToOrganisation.stream()
                    .map(ExperienceDto::toString)
                    .reduce("\n", String::concat);
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There are some experiences still linked to the organisation you want to delete.\n" +
                            "Please delete the link to the following elements and try again later:\n" + dependantExperience);
        }

        Integer organisationDeletedId  = organisationService.deleteOrganisation(organisationId);

        if(organisationDeletedId == 0) {
            throw new UnexpectedCompetenceException("Unable to delete. Organisation with id " + organisationId + " not found.");
        } else {
            try {
                Path path = Paths.get(DIR_TO_UPLOAD + organisationToDelete.getLogoAddress());
                Files.deleteIfExists(path);
            } catch (Exception e) {
                logger.warn("no picture was deleted while deleting the organisation: {} - {} - {}", organisationDeletedId,
                        organisationToDelete.getOrganisationName(), organisationToDelete.getLogoAddress());
            }
        }
        return ResponseEntity.ok().body(organisationToDelete);
    }

}
