package br.com.level4.sicredi.service;

import br.com.level4.sicredi.model.Associado;

import java.util.Optional;

public interface AssociadoService {

    Optional<Associado> obterPorCpf(String cpf);

    Associado salvar(Associado associado);
}
