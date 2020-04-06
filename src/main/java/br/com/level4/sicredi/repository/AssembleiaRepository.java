package br.com.level4.sicredi.repository;

import br.com.level4.sicredi.model.Assembleia;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepository extends PagingAndSortingRepository<Assembleia, Long> {
}
