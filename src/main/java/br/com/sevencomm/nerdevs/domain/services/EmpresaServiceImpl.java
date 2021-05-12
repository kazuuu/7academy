package br.com.sevencomm.nerdevs.domain.services;
import br.com.sevencomm.nerdevs.data.repositories.EmpresaRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IEmpresaService;
import br.com.sevencomm.nerdevs.domain.models.Empresa;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EmpresaServiceImpl implements IEmpresaService {
private final EmpresaRepository empresaRepository;
public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
this.empresaRepository = empresaRepository;
}
@Override
public List<Empresa> listEmpresas() {
return empresaRepository.findAll();
}
@Override
public Empresa getEmpresaById(Integer empresaId) {
Optional<Empresa> fndEmpresa = empresaRepository.findById(empresaId);
if (!fndEmpresa.isPresent()) {
throw new IllegalArgumentException("Empresa nao encontrada");
}
return empresaRepository.getEmpresaById(empresaId);
}
@Override
public Empresa insertEmpresa(Empresa empresa) {
if (empresa.getNome().equals(""))
throw new IllegalArgumentException("Nome inválido");
if (empresa.getCnpj().length() != 14)
throw new IllegalArgumentException("CNPJ inválido");
if (empresaRepository.findFirstByCnpj(empresa.getCnpj()) != null)
throw new IllegalArgumentException("CNPJ já existente");
if (empresa.getSobre().equals(""))
throw new IllegalArgumentException("Sobre inválido");
return empresaRepository.save(empresa);
}
@Override
public Empresa updateEmpresa(Empresa empresa) {
Optional<Empresa> fndEmpresa = empresaRepository.findById(empresa.getId());
if (!fndEmpresa.isPresent()) {
throw new IllegalArgumentException("Empresa inexistente");
}
if (empresa.getNome().equals(""))
throw new IllegalArgumentException("Nome inválido");
if (empresa.getCnpj().length() != 14)
throw new IllegalArgumentException("CNPJ inválido");
if (!(fndEmpresa.get().getCnpj().equals(empresa.getCnpj())) && empresaRepository.findFirstByCnpj(empresa.getCnpj()) != null)
throw new IllegalArgumentException("CNPJ já existente");
if (empresa.getSobre().equals(""))
throw new IllegalArgumentException("Sobre inválido");
return empresaRepository.save(empresa);
}
@Override
public Boolean deleteEmpresa(Integer empresaId) {
Optional<Empresa> fndEmpresa = empresaRepository.findById(empresaId);
if (!fndEmpresa.isPresent()) {
throw new IllegalArgumentException("Empresa não encontrada");
}
empresaRepository.delete(fndEmpresa.get());
return true;
}
}

