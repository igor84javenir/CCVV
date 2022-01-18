package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.Rdv;
import fr.asigroup.ccvv.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RdvService {
    @Autowired
    private RdvRepository rdvrepo;

    public List<Rdv> listRdvs() {
        return (List<Rdv>) rdvrepo.findAll();
    }

    public void enregistrer(Rdv rdv) {
        rdv.setCreatedAt(LocalDateTime.now());
        rdv.setCreatedBy("moi");
        rdv.setModifiedBy("moi");
        rdv.setModifiedAt(LocalDateTime.now());
        rdvrepo.save(rdv);
    }

    public void supprimer(Long id) throws RdvNotFoundException {
        Long count = rdvrepo.countById(id);
        if (count == null || count == 0) {
            throw new RdvNotFoundException("le RDV " + id + "exists pas");
        }
        /*rdvrepo.deleteById(id);*/
        Optional<Rdv> optionalRdv = rdvrepo.findById(id);

        if (optionalRdv.isPresent()) {
            Rdv rdv = optionalRdv.get();
            rdv.setExist(false);
            rdvrepo.save(rdv);
        }


    }

    public List<Rdv> getAllExist(boolean oui){
        return rdvrepo.findAllByExist(oui);
    }

    public Rdv getRdv(Long id) throws RdvNotFoundException {
        Optional<Rdv> rdv = rdvrepo.findById(id);
        if(rdv.isPresent())
            return rdv.get();
        throw new RdvNotFoundException("l'utilisateur" + id + "n'existe pas");
    }

    public List<Rdv> getAll() {
        return rdvrepo.findAll();
    }
}

