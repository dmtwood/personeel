package be.vdab.personeel.domain;

import be.vdab.personeel.domain.Werknemer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Defines Entity class Jobtitel.
 * Mapped to jobtitels table.
 */
@Entity
@Table(name = "jobtitels")
public class Jobtitel  {

    /*******************/
    // MEMBERS VARS
    /*******************/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String naam;

    @Version
    private long versie;

    /**
     * A Jobtitle can be shared by a Set Werknemers
     * full mapping is done in (Many) Werknemer class >> using a joinculumn on jobtitelid
     *
     */
    @OneToMany( mappedBy = "jobtitel")
    private Set<Werknemer> werknemers;


    /*******************/
    // CONSTRUCTOR
    /*******************/

    /**
     * entity class gets a protected default constructor to prevent default public access
     */
    protected Jobtitel() {
    }

    public Jobtitel(@NotBlank String naam, long versie) {
        this.naam = naam;
        this.versie = versie;
        this.werknemers = new LinkedHashSet<>();
    }

    /*******************/
    // GETTERS & SETTERS
    /*******************/

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getVersie() {
        return versie;
    }

    public Set<Werknemer> getWerknemers() {
        return werknemers;
    }
}
