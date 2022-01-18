package fr.asigroup.ccvv.service;

import fr.asigroup.ccvv.entity.ReasonRdv;
import fr.asigroup.ccvv.repository.ReasonRdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ReasonRdvService {

    @Autowired
    // Repository = liaison avec la dbb
    private ReasonRdvRepository repository;

    public List<ReasonRdv> getAll() {

        List<ReasonRdv> liste = (List<ReasonRdv>) repository.findAll();

        return liste;
    }

    public void save(ReasonRdv reasonRdv) {

        repository.save(reasonRdv);
    }
    public void update(ReasonRdv reasonRdv) {

        repository.save(reasonRdv);
    }

    public void delete(Long id) throws ReasonRdvNotFoundException {

        repository.deleteById(id);

    }

    public ReasonRdv getReasonRdv(Long id)   {
        Optional<ReasonRdv> reason = repository.findById(id);

        return reason.get();

    }


}
