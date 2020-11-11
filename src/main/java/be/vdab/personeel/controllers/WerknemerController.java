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

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/11/2020
 * Maps President- ("/werknemer), werknemer- ("/werknemer/optwerknemer") and opslag- ("/werknemer/optwerknemer/opslag") requests to werknemer.html.
 */
@Controller
@RequestMapping("/werknemer")
class WerknemerController {

    /*******************/
    // MEMBERS VARS
    /*******************/
    private final WerknemerService werknemerService;

    private static final String WERKNEMER = "werknemer";
    private static final String OPSLAG = "opslag";
    private static final String REDIRECT_WERNEMER= "redirect:/werknemer/{id}";
    private static final String REDIRECT_WERNEMER_OPSLAG = "redirect:/werknemer/{id}/opslag";


    /*******************/
    // CONSTRUCTOR
    /*******************/
    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }


    /*******************/
    // MAPPINGS
    /*******************/

    /**
     * Searches the President (werknemer with highest hierarchy) and adds him to ModelAndView werknemer.
     * The Model then makes links to all werknemers that report to the President as /werknemer/{id}.
     * Handles requests to /werknemer.
     * @return ModelAndView werknemer.html with Werknemer President if present.
     */
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


    /**
     * Searches a werknemer and adds him to ModelAndView werknemer.
     * The Model then makes links to his superior as /werknemer/{id}.
     * The Model then makes links to all werknemers that report to him as /werknemer/{id}.
     * Handles requests to /werknemer/{optWerknemer}.
     * @param           optWerknemer The werknemer clicked to view his details. PathVariable.
     * @return ModelAndView werknemer.html with the searched Werknemer, his boss and his team-members, if present.
     */
    @GetMapping("{optWerknemer}")
    public ModelAndView werknemer(
            @PathVariable Optional<Werknemer> optWerknemer
    ) {
        ModelAndView mAV = new ModelAndView(WERKNEMER);
        optWerknemer.ifPresent(
                werknemer -> mAV.addObject(
                        WERKNEMER,
                        werknemer
                )
        );
        return mAV;
    }


    /**
     * Adds empty Opslagform for this Werknemer to ModelAndView opslag.
     * The Model then takes input posts it to postOpslag method, in this class.
     * Handles requests to /werknemer/{optWerknemer}/opslag.
     * @param optWerknemer      The Werknemer getting a raise.
     * @param opslagForm        The form that handles the amount inputted as raise.
     * @param errors            Catches possible input errors in form.
     * @return ModelAndView opslag.html with the searched Werknemer a form to handle a raise input.
     */
    @GetMapping("{optWerknemer}/opslag")
    public ModelAndView werknemerOpslag(
            @PathVariable Optional<Werknemer> optWerknemer,
            @Valid OpslagForm opslagForm,
            Errors errors
    ) {
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


    /**
     * Executes a raise.
     * @param id                    The id of the Werknemer getting a raise.
     * @param redirectAttributes    Sets the {id} as attribute for redirection to werknemer to after performing raise.
     * @param opslagForm            Form holding value of the raise.
     * @param errors                Form errors, if any.
     * @return
     */
    @PostMapping("{id}/opslag")
    public String postOpslag(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes,
            @Valid OpslagForm opslagForm,
            Errors errors
    ) {
        redirectAttributes.addAttribute("id", id);

        if( ! errors.hasErrors() ) {
            werknemerService.opslag( id, opslagForm.getOpslag() );
            return REDIRECT_WERNEMER;
        }
        return REDIRECT_WERNEMER_OPSLAG;

    }

}




























