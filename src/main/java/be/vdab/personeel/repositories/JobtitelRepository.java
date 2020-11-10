package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * CRUDs Jobtitel Objects.
 * Jobtitel interface is mapped to the Jobtitel table.
 */
public interface JobtitelRepository extends JpaRepository<Jobtitel, Long> {
    /**
     * Find all objects in the Jobtitel repository.
     * @return a List of Jobtitels
     */
    public List<Jobtitel> findAll();

    /**
     * Find a Jobtitel with given id
     * @param id the Jobtitel record
     * @return Jobtitel if present.
     */
    public Optional<Jobtitel> findById(long id);

}
