package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findAll();

    Location findByLocationId(int id);

    void deleteLocationByLocationId(int id);

    Location save(Location location);
}
