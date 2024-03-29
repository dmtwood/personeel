package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // creates datasource & EntityManager beans
@Sql("/insertJobtitels.sql")
public class JobtitelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String JOBTITELS = "jobtitels";

    private final JobtitelRepository jobtitelRepository;

    public JobtitelRepositoryTest(JobtitelRepository jobtitelRepository) {
        this.jobtitelRepository = jobtitelRepository;
    }

    private long idVanTest() {
        return super.jdbcTemplate.queryForObject(
                "select id from jobtitels where naam = 'Presidente' ", Long.class
        );
    }

    @Test
    void findById() {
        Optional<Jobtitel> jobtitelById = jobtitelRepository.findById(idVanTest());
        assertThat(jobtitelById.get().getNaam()).isEqualTo("Presidente");
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(jobtitelRepository.findById(-1)).isNotPresent();
    }

    @Test
    void findAll() {
        List<Jobtitel> jobtitels = jobtitelRepository.findAll();
        assertThat(jobtitels).hasSize(super.countRowsInTable(JOBTITELS));
    }


}

