package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Serves Jobtitel objects to JobtitelController, when implemented.
 */
public interface JobtitelService {

    /**
     * findall method searches all Jobtitels.
     * @return a list containing all Jobtitels available.
     */
    List<Jobtitel> findAll();

    /**
     * findById method searches a Jobtitel by id.
     * @param id        The id of the Jobtitel needed.
     * @return          The Jobtitel with te ginven id.
     */
    Optional<Jobtitel> findById(long id);
}
