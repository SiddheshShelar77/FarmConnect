
package com.farmconnect.repository;

import com.farmconnect.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Find animals belonging to a farmer
    List<Animal> findByFarmerId(Long farmerId);

    // Find animal using its unique tag number
    Optional<Animal> findByTagNumber(String tagNumber);
}
