package fr.asigroup.ccvv.repository;

import fr.asigroup.ccvv.entity.EntityUnavailableDays;
import fr.asigroup.ccvv.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UnavailableDaysRepository extends JpaRepository<EntityUnavailableDays, Long> {
    Long countById(Long id);

    EntityUnavailableDays findByDate(LocalDate localDate);

   /* List<EntityUnavailableDays> findAllByDispo(EntityUnavailableDays.Dispo dispo);*/
}
