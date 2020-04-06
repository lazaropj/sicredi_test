package br.com.level4.sicredi.repository;

import br.com.level4.sicredi.model.Voto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends PagingAndSortingRepository<Voto, Long> {
}
