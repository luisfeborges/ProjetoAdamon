package adamon.service;

import adamon.model.Adamon;
import adamon.repository.AdamonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdamonService {

    @Autowired
    private AdamonRepository adamonRepository;

    public List<Adamon> findAllAdamons(){
        return adamonRepository.findAll();
    }

    public Adamon finById(Long id){
        return adamonRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado!"));
    }
    public Adamon createNewAdamon(Adamon adamon){
        return adamonRepository.save(adamon);
    }
}
