package be.vdab.personeel.forms;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OpslagForm {

    @NotNull
    @Positive
    @NumberFormat
    private final BigDecimal opslag;

    public OpslagForm(@NotNull @Positive BigDecimal opslag) {
        this.opslag = opslag;
    }

    public BigDecimal getOpslag() {
        return opslag;
    }
}
