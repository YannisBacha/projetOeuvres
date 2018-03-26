package com.epul.oeuvres.metier;

import java.io.Serializable;

/**
 * Created by christian on 19/02/2017.
 */
public class ReservationEntityPK implements Serializable {
    private int idOeuvrevente;
    private int idAdherent;

    public int getIdOeuvrevente() {
        return idOeuvrevente;
    }

    public void setIdOeuvrevente(int idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }


    public ReservationEntityPK() {
    }

    public ReservationEntityPK(int oeuvre, int adherent) {
        setIdOeuvrevente(oeuvre);
        setIdAdherent(adherent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntityPK that = (ReservationEntityPK) o;

        if (idOeuvrevente != that.idOeuvrevente) return false;
        if (idAdherent != that.idAdherent) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOeuvrevente;
        result = 31 * result + idAdherent;
        return result;
    }
}
