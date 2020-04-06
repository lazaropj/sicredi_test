package br.com.level4.sicredi.repository;

import br.com.level4.sicredi.model.Associado;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends PagingAndSortingRepository<Associado, Long> {

    Optional<Associado> findByCpf(String cpf);
}
