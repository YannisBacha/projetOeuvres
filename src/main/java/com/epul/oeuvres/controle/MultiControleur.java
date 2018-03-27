package com.epul.oeuvres.controle;

//import javax.servlet.ServletContext;

import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ReservationEntity;
import com.epul.oeuvres.metier.ReservationEntityPK;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Controller
public class MultiControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    //region LISTER
    @RequestMapping(value = "listerAdherent.htm")
    public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            Service unService = new Service();
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "listerAdherent";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "listerOeuvre.htm")
    public ModelAndView afficherLesOeuvres(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            Service unService = new Service();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "listerOeuvre";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }
    //endregion

    //region PAGE INSERTION/MODIFICATION
    @RequestMapping(value = "ajouterOeuvre.htm")
    public ModelAndView ajouterOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            Service unService = new Service();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaires());
            destinationPage = "ajouterOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterAdherent.htm")
    public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            destinationPage = "ajouterAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierAdherent.htm", method = RequestMethod.GET)
    public ModelAndView modifierAdherent(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {
        String destinationPage = "";
        try {
            Service unService = new Service();
            request.setAttribute("monAdherent", unService.adherentById(getId));
            destinationPage = "modifierAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierOeuvre.htm", method = RequestMethod.GET)
    public ModelAndView modifierOeuvre(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {
        String destinationPage = "";
        try {
            Service unService = new Service();
            request.setAttribute("monOeuvre", unService.oeuvreById(getId));
            destinationPage = "modifierOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }
    //endregion

    //region INSERTION/MODIFICATION/SUPPRESSION BASE
    @RequestMapping(value = "insererAdherent.htm")
    public ModelAndView insererAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            AdherentEntity unAdherent = new AdherentEntity();
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));
            Service unService = new Service();
            unService.insertObjet(unAdherent);
            destinationPage = "redirect:/listerAdherent.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insertOeuvre.htm")
    public ModelAndView insertOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            OeuvreventeEntity uneOeuvre = new OeuvreventeEntity();
            Service unService = new Service();
            uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
            uneOeuvre.setPrixOeuvrevente(Double.parseDouble(request.getParameter("prix")));
            uneOeuvre.setProprietaireOeuvrevente(unService.proprietaireById(Integer.parseInt(request.getParameter("proprietaire"))));
            uneOeuvre.setEtatOeuvrevente("L");
            unService.insertObjet(uneOeuvre);
            destinationPage = "redirect:/listerOeuvre.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerAdherent.htm", method = RequestMethod.GET)
    public ModelAndView supprimerAdherent(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {

        String destinationPage = "";
        try {
            Service unService = new Service();
            unService.supprimerAdherent(getId);
            destinationPage = "redirect:/listerAdherent.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modificationAdherent.htm")
    public ModelAndView modificationAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = null;
        try {
            Service unService = new Service();
            AdherentEntity unAdherent = unService.adherentById(Integer.parseInt(request.getParameter("id")));
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));
            unService.modifierObjet(unAdherent);
            destinationPage = "redirect:/listerAdherent.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modificationOeuvre.htm")
    public ModelAndView modificationOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = null;
        try {
            Service unService = new Service();
            OeuvreventeEntity uneOeuvre = unService.oeuvreById(Integer.parseInt(request.getParameter("id")));
            uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
            uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("prix")));
            uneOeuvre.setProprietaireOeuvrevente(unService.proprietaireById(Integer.parseInt(request.getParameter("proprietaire"))));
            unService.modifierObjet(uneOeuvre);
            destinationPage = "redirect:/listerOeuvre.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "reserverOeuvre.htm")
    public ModelAndView reserverOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("idOeuvre"));
            int idAdherent = Integer.parseInt(request.getParameter("adherent"));
            Service unService = new Service();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            ReservationEntity uneReservation = new ReservationEntity(new ReservationEntityPK(idOeuvre, idAdherent));
            uneReservation.setDateReservation(new Date(formatter.parse(request.getParameter("date_reservation")).getTime()));
            uneReservation.setStatut("encours");
            unService.insertObjet(uneReservation);

            OeuvreventeEntity uneOeuvre = unService.oeuvreById(idOeuvre);
            uneOeuvre.setEtatOeuvrevente("R");
            unService.modifierObjet(uneOeuvre);

            destinationPage = "redirect:/listerOeuvre.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "cancelReservation.htm", method = RequestMethod.GET)
    public ModelAndView cancelReservation(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {
        String destinationPage = "";
        try {
            Service unService = new Service();
            unService.supprimerReservation(this.getReservation(getId));
            OeuvreventeEntity uneOeuvre = unService.oeuvreById(getId);
            uneOeuvre.setEtatOeuvrevente("L");
            unService.modifierObjet(uneOeuvre);
            destinationPage = "redirect:/listerOeuvre.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "validReservation.htm", method = RequestMethod.GET)
    public ModelAndView validReservation(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {
        String destinationPage = "";
        try {
            Service unService = new Service();
            ReservationEntity uneReservation = this.getReservation(getId);
            uneReservation.setStatut("confirmee");
            unService.modifierObjet(uneReservation);
            OeuvreventeEntity uneOeuvre = unService.oeuvreById(getId);
            uneOeuvre.setEtatOeuvrevente("C");
            unService.modifierObjet(uneOeuvre);
            destinationPage = "redirect:/listerOeuvre.htm";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    public ReservationEntity getReservation(int idOeuvre) throws MonException {
        Service unService = new Service();
        ReservationEntity uneReservation = null;
        ReservationEntityPK uneReservationPK = new ReservationEntityPK();
        uneReservationPK.setIdOeuvrevente(idOeuvre);
        List<AdherentEntity> adherents = unService.consulterListeAdherents();
        for (AdherentEntity adherent : adherents) {
            uneReservationPK.setIdAdherent(adherent.getIdAdherent());
            uneReservation = unService.reservationByPK(uneReservationPK);
            if (uneReservation != null)
                break;
        }
        return uneReservation;
    }
    //endregion

    //region AUTRE
	@RequestMapping(value = "index.htm", method = RequestMethod.GET)
	public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("home");
	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("home");
    }

	@RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
	public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("Erreur");
	}
    //endregion
}
