package br.com.level4.sicredi.service.impl;

import br.com.level4.sicredi.model.Pauta;
import br.com.level4.sicredi.repository.PautaRepository;
import br.com.level4.sicredi.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl( PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
