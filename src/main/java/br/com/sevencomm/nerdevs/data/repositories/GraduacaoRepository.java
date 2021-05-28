package br.com.sevencomm.nerdevs.data.repositories;

import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GraduacaoRepository extends JpaRepository<Graduacao, Integer> {

    List<Graduacao> findByUserId(Integer userId);
    
}