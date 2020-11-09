package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {

    /**
     * Find the Werknemer without any Chef, a.k.a. the Big Boss
     * @return a Werknemer is present
     */
    Optional<Werknemer> findByChefIsNull();

    /**
     * Find Werknemer(s) sharing a Jobtitel (peers)
     * @param jobtitel differentiate wich Jobtitel
     * @return a List of Werknemer(s)
     */
    List<Werknemer> findByJobtitel(Jobtitel jobtitel);


}
