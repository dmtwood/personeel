package be.vdab.personeel.domain;

import be.vdab.personeel.domain.Werknemer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "jobtitels")
public class Jobtitel  {

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
