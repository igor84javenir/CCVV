//package fr.asigroup.ccvv.repository;
//
//import fr.asigroup.ccvv.entity.Rdv;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface RdvRepository extends JpaRepository<Rdv, Long> {
//
//    Long countById(Long id);
//    List<Rdv> findAllByExist(boolean exist);
//}