package com.ecommerce.microcommerce.web.serviceImpl;

import com.ecommerce.microcommerce.Services.IOrganisationService;
import com.ecommerce.microcommerce.model.Organisation;
import com.ecommerce.microcommerce.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements IOrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public List<Organisation> getAllOrganisation() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationbyId(Integer organisationId) {
        return organisationRepository.findByOrganisationId(organisationId);
    }

    @Override
    public Organisation postOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation putOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }
}
