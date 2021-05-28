package br.com.sevencomm.nerdevs.data.repositories;

import br.com.sevencomm.nerdevs.domain.models.Escola;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
    Escola getEscolaById(Integer escolaId);
    
    Escola findFirstByCnpj(String cnpj);

}
