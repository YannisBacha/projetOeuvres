package com.epul.oeuvres.controle;

//import javax.servlet.ServletContext;

import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.epul.metier.*;
//import com.epul.meserreurs.*;

///
/// Les m�thode du contr�leur r�pondent � des sollicitations
/// des pages JSP

@Controller
public class MultiControleur {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

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

	@RequestMapping(value = "insererAdherent.htm")
	public ModelAndView insererAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			AdherentEntity unAdherent = new AdherentEntity();
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));
			Service unService = new Service();
			unService.insertAdherent(unAdherent);
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";
		}
		destinationPage = "home";
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "supprimerAdherent.htm", method = RequestMethod.GET)
	public ModelAndView supprimerAdherent(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int getId) throws Exception {

		String destinationPage = "";
		try {
            Service unService = new Service();
            unService.supprimerAdherent(getId);
            // On recharge la liste des adhérents
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "listerAdherent";
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

    @RequestMapping(value = "modificationAdherent.htm")
    public ModelAndView modificationAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = null;
        try {
            Service unService = new Service();
            AdherentEntity unAdherent = unService.adherentById(Integer.parseInt(request.getParameter("id")));
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));
            unService.modifierAdherent(unAdherent);
            // On recharge la liste des adhérents
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "listerAdherent";
        } catch (Exception e) {
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
            destinationPage = "listerOeuvre";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

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
            unService.insertOeuvre(uneOeuvre);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        destinationPage = "home";
        return new ModelAndView(destinationPage);
    }

	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "index.htm", method = RequestMethod.GET)
	public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("home");
	}

	// /
		// / Affichage de la page d'accueil
		// /
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
			return new ModelAndView("home");
		}
	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
	public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("Erreur");
	}
	
	

}
