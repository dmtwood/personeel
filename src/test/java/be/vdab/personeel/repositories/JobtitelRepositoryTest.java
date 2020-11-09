package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/insertJobtitels.sql")
public class JobtitelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String JOBTITELS = "jobtitels";

    private final JobtitelRepository jobtitelRepository;

    public JobtitelRepositoryTest(JobtitelRepository jobtitelRepository) {
        this.jobtitelRepository = jobtitelRepository;
    }

    private long idVanTest() {
        return super.jdbcTemplate.queryForObject(
                "select id from jobtitels where naam = 'test' ", Long.class
        );
    }

    @Test
    void findById() {
        Optional<Jobtitel> jobtitelById = jobtitelRepository.findById(idVanTest());
        assertThat(jobtitelById.get().getNaam()).isEqualTo("test");
    }


}
