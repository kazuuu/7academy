package br.com.sevencomm.nerdevs.domain.interfaces;

import br.com.sevencomm.nerdevs.domain.models.Graduacao;

import java.util.List;

public interface IGraduacaoService {
    List listGraduacoes();

    Graduacao getGraduacao(Integer graduacaoId);

    Boolean insertGraduacao(Graduacao graduacao);

    Graduacao updateGraduacao(Graduacao graduacao);

    Boolean deleteGraduacao(Integer graduacaoId);

    List listGraduacoesByUserId(Integer userId);
    
}
