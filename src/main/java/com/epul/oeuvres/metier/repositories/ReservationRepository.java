package com.epul.oeuvres.metier.repositories;

import com.epul.oeuvres.metier.ReservationEntity;
import com.epul.oeuvres.metier.ReservationEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReservationRepository extends JpaRepository<ReservationEntity, ReservationEntityPK> {
    ReservationEntity findReservationEntityByIdOeuvrevente(int id);
}
