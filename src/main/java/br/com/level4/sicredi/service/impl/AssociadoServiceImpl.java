package br.com.level4.sicredi.service.impl;

import br.com.level4.sicredi.model.Associado;
import br.com.level4.sicredi.repository.AssociadoRepository;
import br.com.level4.sicredi.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private AssociadoRepository associadoRepository;

    @Autowired
    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Optional<Associado> obterPorCpf(String cpf) {
        return associadoRepository.findByCpf(cpf);
    }

    @Override
    public Associado salvar(Associado associado) {
        return associadoRepository.save(associado);
    }
}
