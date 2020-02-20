package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {

    List<Organisation> findAll();

    Organisation findByOrganisationId(int id);

    void deleteOrganisationByOrganisationId(int id);

    Organisation save(Organisation organisation);
}
