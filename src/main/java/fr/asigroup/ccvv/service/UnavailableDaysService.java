package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.EntityUnavailableDays;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.repository.UnavailableDaysRepository;
import fr.asigroup.ccvv.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnavailableDaysService {
    @Autowired
    private UnavailableDaysRepository repo;

    public List<EntityUnavailableDays> listAll(){
        return (List<EntityUnavailableDays>) repo.findAll();
    }

    public String save(EntityUnavailableDays entityUnavailableDays) {
        LocalDate newEntityUnavailabledaysDate = entityUnavailableDays.getDate();

        EntityUnavailableDays existingEntityUnavailableDays = repo.findByDate(newEntityUnavailabledaysDate);

        if (existingEntityUnavailableDays == null) {
            entityUnavailableDays.setCreatedBy(CurrentUser.getCurrentUserDetails().getUsername());
            entityUnavailableDays.setCreatedAt(LocalDateTime.now());
            repo.save(entityUnavailableDays);
            return "ok";
        } else {
            return "exist deja";
        }



    }

    public EntityUnavailableDays get(Long id) throws EntityUnavailableDaysNotFoundException{
        Optional<EntityUnavailableDays> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new EntityUnavailableDaysNotFoundException("RAS");

    }

    public EntityUnavailableDays getByDate(LocalDate localDate) {
        EntityUnavailableDays result = repo.findByDate(localDate);

            return result;
    }

    public void delete(Long id) throws EntityUnavailableDaysNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new EntityUnavailableDaysNotFoundException("RAS");
        }
        Optional<EntityUnavailableDays> optionalunavailable = repo.findById(id);

        if (optionalunavailable.isPresent()) {
            EntityUnavailableDays entityUnavailableDays = optionalunavailable.get();
            /*entityUnavailableDays.setDispo(EntityUnavailableDays.Dispo.INDISPONIBLE);*/
            repo.save(entityUnavailableDays);
        }
        repo.deleteById(id);

    }

}
