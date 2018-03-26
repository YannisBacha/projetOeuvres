package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;

import javax.persistence.EntityTransaction;
import java.util.List;

public class Service extends EntityService{
	public void insertObjet(Object o) throws MonException {
		EntityTransaction transac = null;
		try {
			transac = startTransaction();
			transac.begin();
			entitymanager.persist(o);
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

    public void modifierObjet(Object o) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(o);
            transac.commit();
            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

	public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {
		List<OeuvreventeEntity> mesOeuvres = null;
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesOeuvres = (List<OeuvreventeEntity>) entitymanager.createQuery("SELECT o FROM OeuvreventeEntity o ORDER BY o.titreOeuvrevente").getResultList();
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesOeuvres;
	}

	public List<ProprietaireEntity> consulterListeProprietaires() throws MonException {
		List<ProprietaireEntity> mesProprietaires = null;
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			mesProprietaires = (List<ProprietaireEntity>) entitymanager.createQuery("SELECT p FROM ProprietaireEntity p ORDER BY p.nomProprietaire").getResultList();
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mesProprietaires;
	}

	public AdherentEntity adherentById(int numero) throws MonException {
		AdherentEntity adherent = new AdherentEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
            adherent = entitymanager.find(AdherentEntity.class, numero);
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adherent;
	}

	public OeuvreventeEntity oeuvreById(int numero) throws MonException {
		OeuvreventeEntity oeuvre = new OeuvreventeEntity();
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			oeuvre = entitymanager.find(OeuvreventeEntity.class, numero);
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oeuvre;
	}

	public ProprietaireEntity proprietaireById(int numero) throws MonException {
		ProprietaireEntity proprietaire = null;
		try {
			EntityTransaction transac = startTransaction();
			transac.begin();
			proprietaire = entitymanager.find(ProprietaireEntity.class, numero);
			entitymanager.close();
		} catch (RuntimeException e) {
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proprietaire;
	}

    public ReservationEntity reservationByPK(ReservationEntityPK reservationEntityPK) throws MonException {
        ReservationEntity reservationEntity = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            reservationEntity = entitymanager.find(ReservationEntity.class, reservationEntityPK);
            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservationEntity;
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

    public void supprimerReservation(ReservationEntity reservationEntity) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("DELETE FROM ReservationEntity r WHERE r.idAdherent = :numeroA AND r.idOeuvre = :numeroB")
                    .setParameter("numeroA", reservationEntity.getIdAdherent()).setParameter("numeroB", reservationEntity.getIdOeuvrevente()).executeUpdate();
            transac.commit();
            entitymanager.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
