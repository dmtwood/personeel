package be.vdab.personeel.domain;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "werknemers")
public class Werknemer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    String familienaam;

    @NotBlank
    String voornaam;

    @NotBlank
    @Email
    String email;

    @NotNull
    @PositiveOrZero
    private BigDecimal salaris;

    @NotBlank
    private String paswoord;

    @NotNull
    @DateTimeFormat(style = "S-")
    private LocalDate geboorte;

    @Version
    int versie;


    /**
     * Many employees can share one Jobtitel.
     * Every Jobtitel has a Set of Werknemer
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "jobtitelid")
    private Jobtitel jobtitel;

    /**
     * this is an intern relation, every Chef is a Werknemer and can be responsible for none, one or multiple Werknemer
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "chefid")
    private Werknemer chef;

    /**
     * A Werknemer with chefid can have a set of Werknemers
     */
    @OneToMany( mappedBy = "chef" )
    private Set<Werknemer> teamChef;



}
