package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.model.Experience;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    List<Experience> findAll();

    Experience findByExperienceId(int id);

    void deleteExperienceByExperienceId(int id);

    Experience save(Experience experience);
}
