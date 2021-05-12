package br.com.sevencomm.nerdevs.domain.services;
import br.com.sevencomm.nerdevs.application.dto.IsJoinedDTO;
import br.com.sevencomm.nerdevs.data.repositories.EmpresaRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.data.repositories.VagaRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IVagaService;
import br.com.sevencomm.nerdevs.domain.models.Empresa;
import br.com.sevencomm.nerdevs.domain.models.User;
import br.com.sevencomm.nerdevs.domain.models.Vaga;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class VagaServiceImpl implements IVagaService {
private VagaRepository vagaRepository;
private UserRepository userRepository;
private EmpresaRepository empresaRepository;
public VagaServiceImpl(VagaRepository _vagaRepository, EmpresaRepository _empresaRepository, UserRepository _userRepository) {
vagaRepository = _vagaRepository;
empresaRepository = _empresaRepository;
userRepository = _userRepository;
}
@Override
public List<Vaga> listVagas() {
List<Vaga> listVagasRetorno = vagaRepository.findAll();
for (Vaga vaga: listVagasRetorno)
vaga.setUsers(null);
return listVagasRetorno;
}
@Override
public List<Vaga> listVagasBySalarioRange(Integer min, Integer max) {
int i;
List<Vaga> todasVagas = vagaRepository.findAll();
List<Vaga> listVagas = new ArrayList<>();
for (i = 0; i != todasVagas.size(); i++) {
Vaga v = todasVagas.get(i);
if (v.getSalario() >= min && v.getSalario() <= max)
v.setUsers(null);
listVagas.add(v);
}
return listVagas;
}
@Override
public Vaga getVaga(Integer vagaId) {
Optional<Vaga> fndVaga = vagaRepository.findById(vagaId);
if (!fndVaga.isPresent()) throw new IllegalArgumentException("Vaga não encontrada");
List<User> listUsersRetorno = new ArrayList<>();
for (User user: fndVaga.get().getUsers()) {
user.setCpf(null);
user.setPassword(null);
listUsersRetorno.add(user);
}
fndVaga.get().setUsers(listUsersRetorno);
return fndVaga.get();
}
@Override
public Boolean insertVaga(Vaga vaga) {
if (vaga.getTitulo().equals(""))
throw new IllegalArgumentException("Título inválido");
if (vaga.getDescricao().equals(""))
throw new IllegalArgumentException("Descrição inválida");
if (vaga.getBeneficios().equals(""))
throw new IllegalArgumentException("Beneficio inválido");
if (vaga.getRequisitos().equals(""))
throw new IllegalArgumentException("Requisito inválido");
if (vaga.getEmpresaId() == null)
throw new IllegalArgumentException("Empresa inválida");
Optional<Empresa> empresa = empresaRepository.findById(vaga.getEmpresaId());
if (!empresa.isPresent())
throw new IllegalArgumentException("Empresa Inexistente");
if (vaga.getQuantidadeVagas() == null)
throw new IllegalArgumentException("Quantidade de vagas inválida");
if (vaga.getSalario() == null)
throw new IllegalArgumentException("Salário inválido");
if (vaga.getTurno().equals(""))
throw new IllegalArgumentException("Turno inválido");
Date data = new Date();
vaga.setDatetimeCreated(data);
vagaRepository.save(vaga);
return true;
}
@Override
public Boolean updateVaga(Vaga vaga) {
Optional<Vaga> fndVaga = vagaRepository.findById(vaga.getId());
if (!fndVaga.isPresent())
throw new IllegalArgumentException("Vaga inexistente");
if (vaga.getTitulo().equals(""))
throw new IllegalArgumentException("Título inválido");
if (vaga.getDescricao().equals(""))
throw new IllegalArgumentException("Descrição inválida");
if (vaga.getBeneficios().equals(""))
throw new IllegalArgumentException("Beneficio inválido");
if (vaga.getRequisitos().equals(""))
throw new IllegalArgumentException("Requisito inválido");
if (vaga.getEmpresaId() == null)
throw new IllegalArgumentException("Empresa inválida");
Optional<Empresa> empresa = empresaRepository.findById(vaga.getEmpresaId());
if (!empresa.isPresent())
throw new IllegalArgumentException("Empresa Inexistente");
if (vaga.getQuantidadeVagas() == null)
throw new IllegalArgumentException("Quantidade de vagas inválida");
if (vaga.getSalario() == null)
throw new IllegalArgumentException("Salário inválido");
if (vaga.getTurno().equals(""))
throw new IllegalArgumentException("Turno inválido");
Date dateCreated = new Date();
vaga.setDatetimeCreated(fndVaga.get().getDatetimeCreated());
Date dateUpdated = new Date();
vaga.setDatetimeUpdated(dateUpdated);
vaga.setUsers(fndVaga.get().getUsers());
vagaRepository.save(vaga);
return true;
}
@Override
public Boolean deleteVaga(Integer vagaId) {
Optional<Vaga> vaga = vagaRepository.findById(vagaId);
if (!vaga.isPresent())
throw new IllegalArgumentException("Vaga inexistente");
vagaRepository.deleteById(vagaId);
return true;
}
@Override
public Boolean joinVaga(Integer vagaId) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
Optional<User> user = userRepository.findById(currentUser.getId());
if(!user.isPresent())
throw new IllegalArgumentException(("Usuário inexistente"));
if (!user.get().getId().equals(currentUser.getId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
Optional<Vaga> vaga = vagaRepository.findById(vagaId);
if (!vaga.isPresent())
throw new IllegalArgumentException("Vaga inexistente");
if (vaga.get().getUsers().contains(user.get()))
throw new IllegalArgumentException("Usuário já está na vaga");
List<User> listUsers = vaga.get().getUsers();
listUsers.add(user.get());
vaga.get().setUsers(listUsers);
vagaRepository.save(vaga.get());
return true;
}
@Override
public Boolean isJoined(IsJoinedDTO dados) {
Optional<User> user = userRepository.findByEmail(dados.getEmail());
Optional<Vaga> vaga = vagaRepository.findById(dados.getVagaId());
if (!vaga.isPresent())
throw new IllegalArgumentException("Vaga inexistente");
if (!vaga.get().getUsers().contains(user.get()))
return false;
return true;
}
}
