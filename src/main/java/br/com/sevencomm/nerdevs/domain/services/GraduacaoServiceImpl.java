package br.com.sevencomm.nerdevs.domain.services;
import br.com.sevencomm.nerdevs.data.repositories.GraduacaoRepository;
import br.com.sevencomm.nerdevs.data.repositories.EscolaRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IGraduacaoService;
import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import br.com.sevencomm.nerdevs.domain.models.Escola;
import br.com.sevencomm.nerdevs.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class GraduacaoServiceImpl implements IGraduacaoService {
private final GraduacaoRepository graduacaoRepository;
private final UserRepository userRepository;
private final EscolaRepository escolaRepository;
public GraduacaoServiceImpl(GraduacaoRepository _graduacaoRepository,
EscolaRepository _escolaRepository,
UserRepository _userRepository) {
graduacaoRepository = _graduacaoRepository;
escolaRepository = _escolaRepository;
userRepository = _userRepository;
}
@Override
public List<Graduacao> listGraduacoes() {
return graduacaoRepository.findAll();
}
public List listGraduacoesByUserId(Integer userId) {
Optional<User> user = userRepository.findById(userId);
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
return graduacaoRepository.findByUserId(userId);
}
public Graduacao getGraduacao(Integer graduacaoId) {
Optional<Graduacao> fndGraduacao = graduacaoRepository.findById(graduacaoId);
if (!fndGraduacao.isPresent()) throw new IllegalArgumentException("Graduação não encontrado");
return fndGraduacao.get();
}
public Boolean insertGraduacao(Graduacao graduacao) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
if (!graduacao.getUserId().equals(currentUser.getId())&& !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
if (graduacao.getUserId() == null)
throw new IllegalArgumentException("Usuário inválido");
Optional<User> user = userRepository.findById(graduacao.getUserId());
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
if (graduacao.getNome().equals(""))
throw new IllegalArgumentException("Nome inválido");
if (graduacao.getEscolaNome() == null)
throw new IllegalArgumentException("Escola inválida");
if (graduacao.getNivelGraduacao().equals(""))
throw new IllegalArgumentException("Nível de Graduação inválido");
if (graduacao.getSemestre() == null)
throw new IllegalArgumentException("Semestre inválido");
if (graduacao.getConcluido() == null)
throw new IllegalArgumentException("Concluído inválido");
if (graduacao.getDataInicio().equals(""))
throw new IllegalArgumentException("Preencha todos os campos");
if (graduacao.getDataFim().equals(""))
throw new IllegalArgumentException("Preencha todos os campos");
graduacaoRepository.save(graduacao);
return true;
}
public Graduacao updateGraduacao(Graduacao graduacao) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
Optional<Graduacao> fndGraduacao = graduacaoRepository.findById(graduacao.getId());
if (!fndGraduacao.isPresent())
throw new IllegalArgumentException("Graduação não encontrado");
if (!graduacao.getUserId().equals(currentUser.getId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
if (graduacao.getUserId() == null)
throw new IllegalArgumentException("Usuário inválido");
Optional<User> user = userRepository.findById(graduacao.getUserId());
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
if (graduacao.getNome().equals(""))
throw new IllegalArgumentException("Nome inválido");
if (graduacao.getNivelGraduacao().equals(""))
throw new IllegalArgumentException("Nível inválido");
if (graduacao.getSemestre() == null)
throw new IllegalArgumentException("Semestre inválido");
if (graduacao.getConcluido() == null)
throw new IllegalArgumentException("Graduação inválido");
if (graduacao.getDataInicio().equals(""))
throw new IllegalArgumentException("Preencha todos os campos");
if (graduacao.getDataFim().equals(""))
throw new IllegalArgumentException("Preencha todos os campos");
graduacaoRepository.save(graduacao);
return graduacao;
}
@Override
public Boolean deleteGraduacao(Integer graduacaoId) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
Optional<Graduacao> fndGraduacao = graduacaoRepository.findById(graduacaoId);
if (!fndGraduacao.isPresent())
throw new IllegalArgumentException("Graduação não encontrado");
if (!currentUser.getId().equals(fndGraduacao.get().getUserId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
graduacaoRepository.deleteById(graduacaoId);
return true;
}
}

