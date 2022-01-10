package fr.asigroup.ccvv.repository;


import fr.asigroup.ccvv.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
