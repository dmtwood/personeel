package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * @author Dimitri.Gevers@gmail.com
 * @version 1.00 11/10/2020
 * Maps jobtitels- ("/jobtitels") and jobtitel- ("/jobtitels/id") requests to jobtitels.html.
 */
@Controller
@RequestMapping("/jobtitels")
class JobtitelController {

    /*******************/
    // MEMBERS VARS
    /*******************/
    private static final String JOBTITEL = "jobtitel";
    private final JobtitelService jobtitelService;
    private final WerknemerService werknemerService;

    private static final String JOBTITELS = "jobtitels";
    private static final String WERKNEMERS = "werknemers";


    /*******************/
    // CONSTRUCTOR
    /*******************/
    public JobtitelController(JobtitelService jobtitelService, WerknemerService werknemerService) {
        this.jobtitelService = jobtitelService;
        this.werknemerService = werknemerService;
    }


    /*******************/
    // MAPPINGS
    /*******************/
    /**
     * Handles requests to /jobtitels.
     * Searches all jobtitels and adds them to ModelAndView jobtitels.
     * The Model then makes links to /jobtitels/{jobtitel} ,handled by specJobtitel method in this class.
     * @return ModelAndView jobtitels.html with all jobtitels.
     */
    @GetMapping
    public ModelAndView jobtitel() {
        ModelAndView mAV = new ModelAndView(JOBTITELS);
        mAV.addObject(
                JOBTITELS,
                jobtitelService.findAll() );
        return mAV;
    }

    /**
     * Handles requests to /jobtitels/{jobtitel}.
     * Searches all werknemers with the chosen jobtitel and adds them with the jobtitel to ModelAndView jobtitels
     * @return ModelAndView jobtitels.html with the searched jobtitel and its werknemers.
     */
    @GetMapping("{jobtitel}")
    public ModelAndView specJobtitel(@PathVariable Optional<Jobtitel> jobtitel) {
        ModelAndView mAV = new ModelAndView(JOBTITELS);
        mAV.addObject( JOBTITELS, jobtitelService.findAll() );

        jobtitel.ifPresent(
                jobtitel1 -> mAV.addObject(
                        JOBTITEL,
                        jobtitel1
                )
        );
        jobtitel.ifPresent(
                jobtitel1 -> mAV.addObject(
                        WERKNEMERS,
                        mAV.addObject(
                                WERKNEMERS,
                                werknemerService.findByJobtitel( jobtitel1 ) )
                )
        );


        return mAV;
    }
}
