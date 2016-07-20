/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DvdLibraryDAO;
import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeControllerNoAjax {

    private DvdLibraryDAO dao;

    @Inject // This replaces the controller bean & constructor-arg wiring
    // that we've done in the application context xml from before
    public HomeControllerNoAjax(DvdLibraryDAO dao) {
        this.dao = dao; // plug in the parameter into our
        // dao shaped hole reference & property
    }

    @RequestMapping(value = "/displayDvdLibraryNoAjax", method = RequestMethod.GET)
    public String displayDvdLibraryNoAjax(Model model) {

        this.addABunchaDvds();

        // Get a list of your dvds
        List<Dvd> dvds = dao.getAllDvds();

        // Then update the model so that SpringMVC can pass it
        // along to the view that is going to display it..
        model.addAttribute("dvdLibrary", dvds);

        // return the name of the view
        return "noajax/otherhome"; // turns into jsp/noajax/home.jsp via VR
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest request) {
        String dvdIdInput = request.getParameter("dvdId");

        if (dvdIdInput != null && !dvdIdInput.isEmpty()) {
            try {
                int dvdId = Integer.parseInt(dvdIdInput);
                dao.removeDvd(dvdId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/bob", method = RequestMethod.GET)
    public String editDvdNoAjax(HttpServletRequest request, Model model) {
        String dvdIdInput = request.getParameter("dvdId");

        if (dvdIdInput != null && !dvdIdInput.isEmpty()) {
            try {
                int dvdId = Integer.parseInt(dvdIdInput);
                Dvd dvd = dao.getDvdById(dvdId);
                model.addAttribute("dvd", dvd);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "redirect:displayDvdLibraryNoAjax";
            }
        }

        return "noajax/otheredit";
    }

    @RequestMapping(value = "editDvdNoAjax", method = RequestMethod.POST)
    public String editDvdNoAjax(@Valid @ModelAttribute("dvd") Dvd xenophormagic,
            BindingResult result) {

        if (result.hasErrors()) {
            return "noajax/otheredit";
        }

        dao.updateDvd(xenophormagic);
        return "redirect:displayDvdLibraryNoAjax";
    }

    @RequestMapping(value = "/newDvdFormNoAjax", method = RequestMethod.GET)
    public String addDvdFormNoAjax() {
        return "noajax/otheradd";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String processNewContactFormRequestNoAjax(HttpServletRequest request, Model model) {
        // Remember, this parameter name matches the NAME of the form input
        // Not the id, the id is for styling purposes, not for processing
        String titleInput = request.getParameter("title");
        String releaseDateInput = request.getParameter("releaseDate");
        String mpaaRatingInput = request.getParameter("mpaaRating");
        String directorInput = request.getParameter("director");
        String studioInput = request.getParameter("studio");

        // All the information has been collected, now we have to put it in an object!
        Dvd dvd = new Dvd();
        dvd.setTitle(titleInput);
        dvd.setReleaseDate(releaseDateInput);
        dvd.setMpaaRating(mpaaRatingInput);
        dvd.setDirector(directorInput);
        dvd.setStudio(studioInput);

        // Make sure that you update the dao with your new information!!
        dao.addDvd(dvd);

        // If we don't want to do a redirect, we can go to our JSP
        // but we have to make sure that like the other endpoint, we provide that
        // jsp with all the information it needs to be able to do its job.
        // In this case, otherhome, renders all the dvds into a viewable page
        // so we need to retrieve the dvds, and update the model with that info
        // stored under the appropriate attribute name
        model.addAttribute("dvdLibrary", dao.getAllDvds());
        return "noajax/otherhome";

    }

    private void addABunchaDvds() {
        Dvd[] dvds = {
            //  int dvdId, String title, String releaseDate, String mpaaRating, String director, String studio)
            new Dvd(0, "The Lion King", "June 24, 1994", "G", "Roger Allers, Rob Minkoff", "Walt Disney Pictures"),
            new Dvd(1, "Cinderella", "March 4, 1950", "G", "Clyde Geronimi, Wilfred Jackson", "Walt Disney Pictures"),
            new Dvd(2, "Dumbo", "October 31, 1941", "G", "Samuel Armstrong, Norman Ferguson", "Walt Disney Pictures"),
            new Dvd(3, "Pinocchio", "February 23, 1940", "G", "Norman Ferguson, T. Hee", "Walt Disney Pictures"),
            new Dvd(4, "Finding Nemo", "May 30, 2003", "G", "Andrew Stanton, Lee Unkrich", "Walt Disney Pictures"),
            new Dvd(5, "Snow White and the Seven Dwarfs", "February 4, 1937", "G", "William Cottrell, David Hand", "Walt Disney Pictures"),
            new Dvd(6, "Aladdin", "November 25, 1992", "G", "Ron Clements, John Musker", "Walt Disney Pictures"),
            new Dvd(7, "Mary Poppins", "September 11, 1964", "G", "Robert Stevenson", "Walt Disney Pictures"),
            new Dvd(8, "Robin Hood", "December 6, 1973", "G", "Wolfgang Reitherman", "Walt Disney Pictures"),
            new Dvd(9, "Chicken Little", "November 4, 2005", "G", "Mark Dindal", "Walt Disney Pictures")
        };

        Random r = new Random();
        dao.addDvd(dvds[r.nextInt(dvds.length)]);

    }

}
