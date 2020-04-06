package br.com.level4.sicredi.service.impl;

import br.com.level4.sicredi.model.Voto;
import br.com.level4.sicredi.repository.VotoRepository;
import br.com.level4.sicredi.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {

    private VotoRepository votoRepository;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public Voto salvar(Voto voto) {
        return votoRepository.save(voto);
    }
}
