package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Implements JobtitelService.
 * Serves Jobtitel objects to JobtitelController.
 * Takes JobtitelRepository bean.
 */
@Service
@Transactional( readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultJobtitelService implements JobtitelService {

    private final JobtitelRepository jobtitelRepository;

    public DefaultJobtitelService(JobtitelRepository jobtitelRepository) {
        this.jobtitelRepository = jobtitelRepository;
    }


    /**
     * findall method searches all Jobtitels.
     * @return a list containing all Jobtitels available.
     */
    @Override
    public List<Jobtitel> findAll() {
        return jobtitelRepository.findAll();
    }

    /**
     * findById method searches a Jobtitel by id.
     * @param id        The id of the Jobtitel needed.
     * @return          The Jobtitel with te ginven id.
     */
    @Override
    public Optional<Jobtitel> findById(long id) {
        return jobtitelRepository.findById(id);
    }
}
