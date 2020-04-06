package br.com.level4.sicredi.service;

import br.com.level4.sicredi.model.Assembleia;

import java.util.Optional;

public interface AssembleiaService {

    Assembleia salvar(Assembleia assembleia);

    Optional<Assembleia> obterPorId(Long id);

    boolean validarCPF(String cpf);

    void publicarResultado(Assembleia assembleia);
}
