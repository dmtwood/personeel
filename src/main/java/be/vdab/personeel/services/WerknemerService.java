package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Serves Werknemer objects to WerknemerController, when implemented.
 */
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public interface WerknemerService {

    /**
     * findPresident method searches the Big Boss.
     * @return          The Werknemer who is President.
     */
    Optional<Werknemer> findPresident();

    /**
     * findById method searches a Werknemer by id.
     * @param id        The id of the Werknemer needed.
     * @return          The Werknemer with the given id.
     */
    Optional<Werknemer> findById(long id);

    /**
     * findByJobtitel method searches a Werknemer by Jobtitel.
     * @param jobtitel  The jobtitel of the Werknemer(s) needed.
     * @return          The Werknemer(s) with the given Jobtitel.
     */
    Set<Werknemer> findByJobtitel(Jobtitel jobtitel);

    /**
     * opslag method searches a Werknemer and gives him a raise.
     * @param id        The id of the Werknemer needed.
     * @param opslag    The amount by which the salary is raised.
     * @return          The Werknemer with the given id.
     */
    void opslag(long id, BigDecimal opslag);


}
