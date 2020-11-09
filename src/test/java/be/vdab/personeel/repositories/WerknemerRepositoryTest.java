package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    public static final String WERKNEMERS = "werknemers";

    @Autowired
    private final WerknemerRepository werknemerRepository;
    private final JobtitelRepository jobtitelRepository;

    WerknemerRepositoryTest(WerknemerRepository wR, JobtitelRepository jR) {
        this.werknemerRepository = wR;
        this.jobtitelRepository = jR;
    }

    private long idVanBaas() {
        return super.jdbcTemplate.queryForObject(
                "select id from werknemers where chefid is null ", Long.class
        );
    }
//
//    private long idVanJobtitelPresident() {
//        return super.jdbcTemplate.queryForObject(
//                "select id from jobtitels where naam = 'Presidente'", Long.class
//        );
//    }


    @Test
    void findByChefIsNull(){
        Optional<Werknemer> baas = werknemerRepository.findByChefIsNull();
        assertThat(baas.get().getId()).isEqualTo(idVanBaas());
    }

//    @Test
//    void findByJobtitel(){
//        Optional<Jobtitel> jobtitel = jobtitelRepository.findById(idVanBaas());
//        assertThat(jobtitel.stream().count()).isEqualTo(super.countRowsInTableWhere(WERKNEMERS, )
//        Set<Werknemer> werknemersMetGedeeldeJobtitel = werknemerRepository.findByJobtitel( jobtitel );
//    }


}
