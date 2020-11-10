package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;
import be.vdab.personeel.repositories.WerknemerRepository;
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
 * implements WerknemerService
 * Serves Werknemer objects to WerknemerController.
 * Takes WerknemerRepository bean.
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWerknemerService implements WerknemerService {

    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
        this.werknemerRepository = werknemerRepository;
    }

    /**
     * findPresident method searches the Big Boss.
     * @return          The Werknemer who is President.
     */
    @Override
    public Optional<Werknemer> findPresident() {
        return werknemerRepository.findByChefIsNull();
    }

    /**
     * findById method searches a Werknemer by id.
     * @param id        The id of the Werknemer needed.
     * @return          The Werknemer with the given id.
     */
    @Override
    public Optional<Werknemer> findById(long id) {
        return werknemerRepository.findById(id);
    }

    /**
     * findByJobtitel method searches a Werknemer by Jobtitel.
     * @param jobtitel  The jobtitel of the Werknemer(s) needed.
     * @return          The Werknemer(s) with the given Jobtitel.
     */
    @Override
    public Set<Werknemer> findByJobtitel(Jobtitel jobtitel) {
        return werknemerRepository.findByJobtitel(jobtitel);
    }

    /**
     * opslag method searches a Werknemer and gives him a raise.
     * @param id        The id of the Werknemer needed.
     * @param opslag    The amount by which the salary is raised.
     * @return          The Werknemer with the given id.
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void opslag(long id, BigDecimal opslag) {
        werknemerRepository.findById(id)
                .orElseThrow(WerknemerNietGevondenException::new)
                .opslag(opslag);
//                .orElseThrow(() -> new WerknemerNietGevondenException());
    }
}
