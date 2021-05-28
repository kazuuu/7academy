package br.com.sevencomm.nerdevs.domain.interfaces;

import br.com.sevencomm.nerdevs.domain.models.Escola;
import java.util.List;

public interface IEscolaService {

    Escola insertEscola (Escola escola);

    List<Escola> listEscolas();

    Escola getEscolaById(Integer escolaId);

    Escola updateEscola (Escola escola);
    
    Boolean deleteEscola(Integer escolaId);
    
}
