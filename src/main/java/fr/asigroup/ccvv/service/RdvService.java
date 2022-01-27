package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.pojo.AvailableRdvTime;
import fr.asigroup.ccvv.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RdvService {
    @Autowired
    private RdvRepository rdvRepository;

    public List<Rdv> listRdvs() {
        return (List<Rdv>) rdvRepository.findAll();
    }

    public List<Rdv> listRdvsByDate(LocalDate localDate) {
        return (List<Rdv>) rdvRepository.findAllByDateAndTime(localDate);
    }
    public void save(Rdv rdv) {

        System.out.println("HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE HELLO FROM RDV-SERVICE ");

        System.out.println(rdv);

        rdv.setStatus(Rdv.Status.Active);
        rdv.setCreatedAt(LocalDateTime.now());
        rdv.setCreatedBy("System Create");
        rdv.setModifiedBy("System Edit");
        rdv.setModifiedAt(LocalDateTime.now());

        System.out.println(rdv);

        rdvRepository.save(rdv);
    }

    public void supprimer(Long id) throws RdvNotFoundException {
        Long count = rdvRepository.countById(id);
        if (count == null || count == 0) {
            throw new RdvNotFoundException("le RDV " + id + "exists pas");
        }
        /*rdvrepo.deleteById(id);*/
        Optional<Rdv> optionalRdv = rdvRepository.findById(id);

        if (optionalRdv.isPresent()) {
            Rdv rdv = optionalRdv.get();
            rdv.setExist(false);
            rdvRepository.save(rdv);
        }


    }

    public List<Rdv> getAllExist(boolean oui){
        return rdvRepository.findAllByExist(oui);
    }

    public Rdv getRdv(Long id) throws RdvNotFoundException {
        Optional<Rdv> rdv = rdvRepository.findById(id);
        if(rdv.isPresent())
            return rdv.get();
        throw new RdvNotFoundException("l'utilisateur" + id + "n'existe pas");
    }

    public List<Rdv> getAll() {
        return rdvRepository.findAll();
    }

//    public List<AvailableRdvTime> getAvailabiltyOfDay(LocalDate date) {
//        List<Rdv> rdvOfDay = rdvRepository.findAllByDateAndExist(true, date);
//        System.out.println("THIS ARE RDV EXISTS FOR SAME DATE : " + rdvOfDay);
//        return null;
//    }
}

