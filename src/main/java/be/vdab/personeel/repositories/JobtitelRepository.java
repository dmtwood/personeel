package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * CRUD Jobtitel Objects. Jobtitel class is mapped to the Jobtitel table with a primary key (id) of type long
 */
public interface JobtitelRepository extends JpaRepository<Jobtitel, Long> {

    /**
     * Find all objects in the Jobtitel repository.
     * @return a List of Jobtitels
     */
    List<Jobtitel> findAll();

    /**
     * Find a Jobtitel with given id
     * @param id the Jobtitel record
     * @return Jobtitel if present.
     */
    Optional<Jobtitel> findById(long id);

}
