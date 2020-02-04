package com.ecommerce.microcommerce.web.service;

import com.ecommerce.microcommerce.interfaces.IOrganisationService;
import com.ecommerce.microcommerce.model.Organisation;
import com.ecommerce.microcommerce.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService implements IOrganisationService {

    @Autowired
    private OrganisationRepository OrganisationRepository;

    @Override
    public List<Organisation> getAllOrganisation() {
        return OrganisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationbyId(Integer organisationId) {
        return OrganisationRepository.findByOrganisationId(organisationId);
    }

    @Override
    public Organisation postOrganisation(Organisation organisation) {
        return OrganisationRepository.save(organisation);
    }
}
