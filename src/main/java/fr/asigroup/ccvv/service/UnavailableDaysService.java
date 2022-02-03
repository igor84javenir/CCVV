package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.EntityUnavailableDays;
import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.repository.UnavailableDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(EntityUnavailableDays entityUnavailableDays) {
        entityUnavailableDays.setDispo(EntityUnavailableDays.Dispo.DISPONIBLE);
        entityUnavailableDays.setCreatedAt(LocalDateTime.now());
        entityUnavailableDays.setCreatedBy("System Create");
        entityUnavailableDays.setModifiedBy("System Edit");
        entityUnavailableDays.setModifiedAt(LocalDateTime.now());
        repo.save(entityUnavailableDays);
    }

    public EntityUnavailableDays get(Long id) throws EntityUnavailableDaysNotFoundException{
        Optional<EntityUnavailableDays> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new EntityUnavailableDaysNotFoundException("RAS");


    }

    public void cancel(Long id) throws EntityUnavailableDaysNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new EntityUnavailableDaysNotFoundException("RAS");
        }
        Optional<EntityUnavailableDays> optionalunavailable = repo.findById(id);

        if (optionalunavailable.isPresent()) {
            EntityUnavailableDays entityUnavailableDays = optionalunavailable.get();
            entityUnavailableDays.setDispo(EntityUnavailableDays.Dispo.INDISPONIBLE);
            repo.save(entityUnavailableDays);
        }

    }
}
