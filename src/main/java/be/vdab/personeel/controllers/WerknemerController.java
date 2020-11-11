package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.OpslagForm;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/werknemer")
class WerknemerController {

    private static final String WERKNEMER = "werknemer";
    private static final String OPSLAG = "opslag";
    private static final String REDIRECT_WERNEMER= "redirect:/werknemer/{id}";
    private static final String REDIRECT_WERNEMER_OPSLAG = "redirect:/werknemer/{id}/opslag";

    private final WerknemerService werknemerService;


    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }


    @GetMapping
    public ModelAndView findPresident() {
        ModelAndView mAV = new ModelAndView(WERKNEMER);

        werknemerService.findPresident().ifPresent(
                werknemer -> mAV.addObject(
                        WERKNEMER,
                        werknemer
                )
        );
        return mAV;
    }


    @GetMapping("{optWerknemer}/opslag")
    public ModelAndView werknemerOpslag(
            @PathVariable Optional<Werknemer> optWerknemer,
            @Valid OpslagForm opslagForm,
            Errors errors) {
        ModelAndView mAV = new ModelAndView(OPSLAG);

        mAV.addObject(OPSLAG)
                .addObject(new OpslagForm(null));

        optWerknemer.ifPresent(werknemer ->
                 mAV.addObject(
                         WERKNEMER,
                         werknemer)
                );
    return mAV;
    }


    @PostMapping("{id}/opslag")
    public String postOpslag(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes,
            @Valid OpslagForm opslagForm,
            Errors errors) {

        redirectAttributes.addAttribute("id", id);

        if( ! errors.hasErrors() ) {
            werknemerService.opslag( id, opslagForm.getOpslag() );
            return REDIRECT_WERNEMER;
        }
        return REDIRECT_WERNEMER_OPSLAG;

    }
}




























