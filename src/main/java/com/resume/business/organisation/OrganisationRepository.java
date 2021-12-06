package com.resume.business.organisation;

import com.resume.model.organisation.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {

    List<Organisation> findAll();

    Organisation findByOrganisationId(int id);

    Integer deleteOrganisationByOrganisationId(Integer id);

    Organisation save(Organisation organisation);
}
