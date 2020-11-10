package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Sql("/insertJobtitels.sql")
class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    public static final String WERKNEMERS = "werknemers";
    Jobtitel jobtitel;
    @Autowired
    private final WerknemerRepository werknemerRepository;
    private final JobtitelRepository jobtitelRepository;
    EntityManager entityManager;

    WerknemerRepositoryTest(WerknemerRepository wR, JobtitelRepository jR, EntityManager entityManager) {
        this.werknemerRepository = wR;
        this.jobtitelRepository = jR;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void beforeEach(){
            jobtitel = new Jobtitel("test", 9);
            entityManager.persist(jobtitel);
    }

    private long idVanBaas() {
        return super.jdbcTemplate.queryForObject(
                "select id from werknemers where chefid is null ", Long.class
        );
    }


    @Test
    void findByChefIsNull(){
        Optional<Werknemer> baas = werknemerRepository.findByChefIsNull();
        assertThat(baas.get().getId()).isEqualTo(idVanBaas());
    }

    @Test
    void findByJobtitel(){
        Set<Werknemer> crew;
         crew = werknemerRepository.findByJobtitel(jobtitel);

         int test = super.countRowsInTableWhere(WERKNEMERS, "jobtitelid = 9" );
        System.out.println(test);
                          assertThat(
                                  super.countRowsInTableWhere(WERKNEMERS, "jobtitelid = 9"
                                  )
        ).isEqualTo(
                crew.size()
        );

    }


}
