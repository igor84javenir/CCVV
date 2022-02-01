package fr.asigroup.ccvv.repository;

import fr.asigroup.ccvv.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RdvRepository extends JpaRepository<Rdv, Long> {

    Long countById(Long id);
//    List<Rdv> findAllByExist(boolean exist);
    List<Rdv> findAllByStatus(Rdv.Status status);
    List<Rdv> findAllByDateAndStatus(LocalDate date, Rdv.Status status);

    @Query(value="SELECT r FROM Rdv r WHERE r.date = ?1")
    List<Rdv> findAllByDateAndTime(LocalDate date);


}