package br.com.level4.sicredi.repository;

import br.com.level4.sicredi.model.Pauta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends PagingAndSortingRepository<Pauta, Long> {
}
