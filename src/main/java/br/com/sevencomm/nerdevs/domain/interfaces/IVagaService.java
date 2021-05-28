package br.com.sevencomm.nerdevs.domain.interfaces;

import br.com.sevencomm.nerdevs.application.dto.IsJoinedDTO;
import br.com.sevencomm.nerdevs.domain.models.Vaga;

import java.util.List;

public interface IVagaService {
    Vaga getVaga(Integer userId);

    Boolean insertVaga(Vaga vaga);

    Boolean updateVaga(Vaga vaga);

    List<Vaga> listVagas();

    List<Vaga> listVagasBySalarioRange(Integer min, Integer max);

    Boolean deleteVaga(Integer vagaId);

    Boolean joinVaga(Integer vagaId);

    Boolean isJoined(IsJoinedDTO dados);
    
}
