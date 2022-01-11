package fr.asigroup.ccvv.repository;


import fr.asigroup.ccvv.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByExist(boolean exist, Pageable pageable);
}
