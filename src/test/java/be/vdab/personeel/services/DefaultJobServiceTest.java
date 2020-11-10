//package be.vdab.personeel.services;
//
//import be.vdab.personeel.domain.Jobtitel;
//import be.vdab.personeel.exceptions.JobtitelNietGevondenException;
//import be.vdab.personeel.repositories.JobtitelRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.yaml.snakeyaml.tokens.DocumentEndToken;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class DefaultJobServiceTest {
//
//    private DefaultJobtitelService jobtitelService;
//
//    @Mock
//    private JobtitelRepository jobtitelRepository;
//    private Jobtitel jobtitel;
//    private long idVanTestTitel;
//
//    @BeforeEach
//    void beforeEach() {
//        jobtitel = new Jobtitel("testtitel", 1);
//        jobtitelService = new DefaultJobtitelService(jobtitelRepository);
//        idVanTestTitel = jobtitel.getId();
//
//    }
//
//    @Test
//    void findById() {
//
//       jobtitel = jobtitelRepository.findById(idVanTestTitel).orElseThrow(() -> new JobtitelNietGevondenException());
//        assertThat(jobtitel.getNaam()).isEqualTo("testtitel");
//
//    }
//
//}
