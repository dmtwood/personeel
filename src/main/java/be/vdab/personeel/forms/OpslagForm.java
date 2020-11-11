package be.vdab.personeel.forms;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Defines possible user input as raise.
 * Handled by werknemerOpslag and postOpslag methods of WerknemerController.
 */
public class OpslagForm {

    /*******************/
    // MEMBERS VARS
    /*******************/

    @NotNull
    @Positive
    @NumberFormat
    private final BigDecimal opslag;


    /*******************/
    // CONSTRUCTOR
    /*******************/
    public OpslagForm(@NotNull @Positive BigDecimal opslag) {
        this.opslag = opslag;
    }


    /*******************/
    // GETTERS & SETTERS
    /*******************/
    public BigDecimal getOpslag() {
        return opslag;
    }
}
