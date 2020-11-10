package be.vdab.personeel.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Defines Entity class Werknemer.
 * Mapped to werknemers table.
 */
@Entity
@Table(name = "werknemers")
public class Werknemer {

    /*******************/
    // MEMBERS VARS
    /*******************/

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
    @Positive
    @NumberFormat
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
    @JoinColumn( name = "jobtitelid", nullable = true)
    private Jobtitel jobtitel;

    /**
     * this is an intern relation, every Chef is a Werknemer and can be responsible for none, one or multiple Werknemer
     */
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY, cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH})
    @JoinColumn( name = "chefid", nullable = true)
    private Werknemer chef;

    /**
     * A Werknemer with chefid can have a set of Werknemers
     */
    @OneToMany( mappedBy = "chef" )
    private Set<Werknemer> teamChef;


    /*******************/
    // CONSTRUCTOR
    /*******************/

    /**
     * entity class gets a protected default constructor to prevent default public access
     */
    protected Werknemer(){
    }



    /*******************/
    // GETTERS & SETTERS
    /*******************/

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public int getVersie() {
        return versie;
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Set<Werknemer> getTeamChef() {
        return teamChef;
    }

    public void opslag(BigDecimal opslag) {
        salaris = salaris.add(opslag);
    }
}
