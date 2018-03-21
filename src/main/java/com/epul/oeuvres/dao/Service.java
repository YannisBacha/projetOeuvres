package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class Service extends EntityService{

	/* Insertion d'un adherent
	 * param Adherent unAdherent
	 * */
	public void insertAdherent(AdherentEntity unAdherent) throws MonException {
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			entitymanager.persist(unAdherent);
			transac.commit();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/* Lister les adherents
	 * */
	public List<AdherentEntity> consulterListeAdherents() throws MonException {
		List<AdherentEntity> mesAdherents = null;
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesAdherents = (List<AdherentEntity>)entitymanager.createQuery("SELECT a FROM AdherentEntity a ORDER BY a.nomAdherent").getResultList();
			entitymanager.close();
		}
		catch (RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesAdherents;
	}

	/* Consultation d'une adherent par son numéro
	*/
	public AdherentEntity adherentById(int numero) throws MonException {
		List<AdherentEntity> adherents = null;
		AdherentEntity adherent = new AdherentEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			adherents = (List<AdherentEntity>)entitymanager.createQuery("SELECT a FROM AdherentEntity a WHERE a.idAndherent="+numero).getResultList();
			adherent = adherents.get(0);
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adherent;
	}

	public void supprimerAdherent(int numero) throws MonException {
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			entitymanager.createQuery("DELETE FROM AdherentEntity a WHERE a.idAdherent = :numero").setParameter("numero", numero).executeUpdate();
			transac.commit();

			entitymanager.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierAdherent(AdherentEntity adherentEntity) throws MonException {
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();

			entitymanager.createQuery("UPDATE AdherentEntity a SET a.nomAdherent=" + adherentEntity.getNomAdherent() + ", " +
					"a.prenomAdherent=" + adherentEntity.getPrenomAdherent() + ", " +
					"a.villeAdherent=" + adherentEntity.getVilleAdherent() + " " +
					"WHERE a.idAdherent =" + adherentEntity.getIdAdherent()).executeUpdate();
			transac.commit();

			entitymanager.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Lister les adherents
	 * */
	public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {
		List<OeuvreventeEntity> mesOeuvres = null;
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesOeuvres = (List<OeuvreventeEntity>) entitymanager.createQuery("SELECT o FROM OeuvreventeEntity o ORDER BY o.titreOeuvrevente").getResultList();

			// Récuperation du proprietaire
			ProprietaireEntity proprietaireEntity = null;
			for (OeuvreventeEntity oeuvre : mesOeuvres) {
				proprietaireEntity = (ProprietaireEntity) entitymanager.createQuery(("SELECT p FROM ProprietaireEntity p WHERE P.IdProprietaire = : id")).setParameter("id", oeuvre.getIdProprietaire()).getSingleResult();
				oeuvre.setProprietaireOeuvrevente(proprietaireEntity);
			}
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesOeuvres;
	}
}
