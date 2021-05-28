package br.com.sevencomm.nerdevs.domain.services;

import br.com.sevencomm.nerdevs.data.repositories.EscolaRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IEscolaService;
import br.com.sevencomm.nerdevs.domain.models.Escola;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscolaServiceImpl implements IEscolaService {
    private final EscolaRepository escolaRepository;
    
    public EscolaServiceImpl(EscolaRepository _escolaRepository) {
        this.escolaRepository = _escolaRepository;
    }

    @Override
    public List<Escola> listEscolas() {
        return escolaRepository.findAll();
    }

    @Override
    public Escola getEscolaById(Integer escolaId) {
        Optional<Escola> fndEscola = escolaRepository.findById(escolaId);
        if (!fndEscola.isPresent()) {
            throw new IllegalArgumentException("Escola não encontrada");
        }

        return escolaRepository.getEscolaById(escolaId);
    }

    @Override
    public Escola insertEscola(Escola escola) {
        if (escola.getNome().equals(""))
            throw new IllegalArgumentException("Nome inválido");
        if (escola.getCnpj().length() != 14)
            throw new IllegalArgumentException("CNPJ inválido");
        if (escolaRepository.findFirstByCnpj(escola.getCnpj()) != null)
            throw new IllegalArgumentException("CNPJ já existente");
        if (escola.getCep().length() != 8)
            throw new IllegalArgumentException("CEP inválido");

        return escolaRepository.save(escola);
    }

    @Override
    public Escola updateEscola(Escola escola) {
        Optional<Escola> fndEscola = escolaRepository.findById(escola.getId());
        if (!fndEscola.isPresent()) {
            throw new IllegalArgumentException("Escola inexistente");
        }
        if (escola.getNome().equals(""))
            throw new IllegalArgumentException("Nome inválido");
        if (escola.getCnpj().length() != 14)
            throw new IllegalArgumentException("CNPJ inválido");
        if (!(fndEscola.get().getCnpj().equals(escola.getCnpj())) && escolaRepository.findFirstByCnpj(escola.getCnpj()) != null)
            throw new IllegalArgumentException("CNPJ já existente");
        if (escola.getCep().length() != 8)
            throw new IllegalArgumentException("CEP inválido");

        return escolaRepository.save(escola);
    }

    @Override
    public Boolean deleteEscola(Integer escolaId) {
        Optional<Escola> fndEscola = escolaRepository.findById(escolaId);
        if (!fndEscola.isPresent()) {
            throw new IllegalArgumentException("Escola não encontrada");
        }
        escolaRepository.delete(fndEscola.get());

        return true;
    }
    
}