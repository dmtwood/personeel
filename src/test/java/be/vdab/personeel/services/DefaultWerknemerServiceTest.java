package be.vdab.personeel.services;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yaml.snakeyaml.tokens.DocumentEndToken;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultWerknemerServiceTest {

    private DefaultWerknemerService werknemerService;

    @Mock
    private WerknemerRepository werknemerRepository;
    private Werknemer werknemer;

    @BeforeEach
    void beforeEach() {
        werknemer = new Werknemer("achter", "voor", "voor.achter@Test.dev",
                BigDecimal.valueOf(2_500L), "pw", LocalDate.of(1999, 12, 31), 1);
        werknemerService = new DefaultWerknemerService(werknemerRepository);
    }

    @Test
    void opslag() {
        when( werknemerRepository.findById(1L) )
                .thenReturn( Optional.of(werknemer) );

        werknemerService.opslag(1, BigDecimal.valueOf(500L) );
        assertThat(werknemer.getSalaris())
                .isEqualByComparingTo(BigDecimal.valueOf(3_000L));
        verify(werknemerRepository).findById(1L);
    }

    @Test
    void opslagVoorOnbestaandeWerknemer() {
        assertThatExceptionOfType(WerknemerNietGevondenException.class)
                .isThrownBy( () -> werknemerService.opslag(-1, BigDecimal.TEN) );
        verify(werknemerRepository).findById(-1L);
    }
}
