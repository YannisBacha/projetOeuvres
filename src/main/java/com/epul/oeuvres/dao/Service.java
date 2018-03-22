package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;

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
		AdherentEntity adherent = new AdherentEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
            //adherent = (AdherentEntity)entitymanager.createQuery("SELECT a FROM AdherentEntity a WHERE a.idAndherent=:id").setParameter("id", numero).getSingleResult();
            adherent = entitymanager.find(AdherentEntity.class, numero);
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
            entitymanager.merge(adherentEntity);
			transac.commit();
			entitymanager.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /* Lister les oeuvres
	 * */
	public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {
		List<OeuvreventeEntity> mesOeuvres = null;
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
            mesOeuvres = (List<OeuvreventeEntity>) entitymanager.createQuery("SELECT o FROM OeuvreventeEntity o").getResultList();
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesOeuvres;
	}
}
