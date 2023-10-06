package com.example.demo.servicesImpl;

import com.example.demo.beans.Responce;
import com.example.demo.repositories.ResponceRepository;
import com.example.demo.services.ResponceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponceServiceImpl implements ResponceService {

    @Autowired
    private ResponceRepository responceRepository;

    @Override
    public Responce create(Responce responce) {
        switch (responce.getText()){
            case "Tout_A_Fait_Daccord" : responce.setNote(4);break;
            case "Daccord" : responce.setNote(3);break;
            case "Neutre" : responce.setNote(2);break;
            case "Pas_daccord" : responce.setNote(1);break;
            case "Desaccord_Total" : responce.setNote(0);break;
            default:break;
        }
        return responceRepository.save(responce);
    }

    @Override
    public Responce update(Responce responce, Long id) {
        if(responceRepository.findById(id) != null){
            Responce resToUpdate = responceRepository.findResponceById(id);
            resToUpdate.setText(responce.getText());
            return responceRepository.findResponceById(resToUpdate.getId());
        }else{
            return  null;
        }
    }

    @Override
    public String delete(Long id) {
        responceRepository.deleteById(id);
        return "Responce has deleted successfully";
    }

    @Override
    public Responce get(Long id) {
        return responceRepository.findResponceById(id);
    }

    @Override
    public List<Responce> getAll() {
        return responceRepository.findAll();
    }
}
