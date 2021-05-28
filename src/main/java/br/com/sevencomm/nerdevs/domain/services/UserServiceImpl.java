package br.com.sevencomm.nerdevs.domain.services;

import br.com.sevencomm.nerdevs.application.dto.*;
import br.com.sevencomm.nerdevs.data.repositories.GraduacaoRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.data.repositories.VagaRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IUserService;
import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import br.com.sevencomm.nerdevs.domain.models.Role;
import br.com.sevencomm.nerdevs.domain.models.User;
import br.com.sevencomm.nerdevs.domain.models.Vaga;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private VagaRepository vagaRepository;
    private GraduacaoRepository graduacaoRepository;
    
    public UserServiceImpl(UserRepository _userRepository, VagaRepository _vagaRepository, GraduacaoRepository _graduacaoRepository) {
        userRepository = _userRepository;
        vagaRepository = _vagaRepository;
        graduacaoRepository = _graduacaoRepository;
    }

    public List<UsuarioPublicoDTO> listUsers() {
        List<User> lista = userRepository.findAll();
        List<UsuarioPublicoDTO> listaDTO = new ArrayList<UsuarioPublicoDTO>();

        for (int i = 0; i < lista.size(); i++) {
            listaDTO.add(new UsuarioPublicoDTO());
            listaDTO.get(i).setId(lista.get(i).getId());
            listaDTO.get(i).setLogin(lista.get(i).getLogin());
            listaDTO.get(i).setNome(lista.get(i).getNome());
            listaDTO.get(i).setEmail(lista.get(i).getEmail());
            listaDTO.get(i).setSexo(lista.get(i).getSexo());
            listaDTO.get(i).setCep(lista.get(i).getCep());
            listaDTO.get(i).setIndicacao(lista.get(i).getIndicacao());
            listaDTO.get(i).setDescricao(lista.get(i).getDescricao());
            listaDTO.get(i).setRoles(lista.get(i).getRoles());
            //            listaDTO.get(i).setGraduacoes(lista.get(i).getGraduacoes());
    }
        return listaDTO;
    }

    public UsuarioPublicoDTO getUser(Integer userId) {
        Optional<User> fndUser = userRepository.findById(userId);
        if (!fndUser.isPresent())
            throw new IllegalArgumentException("Usuário inexistente");

        UsuarioPublicoDTO retUser = new UsuarioPublicoDTO();
        retUser.setId(fndUser.get().getId());
        retUser.setLogin(fndUser.get().getLogin());
        retUser.setNome(fndUser.get().getNome());
        retUser.setEmail(fndUser.get().getEmail());
        retUser.setSexo(fndUser.get().getSexo());
        retUser.setCep(fndUser.get().getCep());
        retUser.setIndicacao(fndUser.get().getIndicacao());
        retUser.setDescricao(fndUser.get().getDescricao());
        retUser.setRoles(fndUser.get().getRoles());
        //        retUser.setGraduacoes(fndUser.get().getGraduacoes());

        return retUser;
    }

    public UsuarioPublicoDTO getUserByEmail(String email) {
        Optional<User> fndUser = userRepository.findByEmail(email);
        if (!fndUser.isPresent())
            throw new IllegalArgumentException("Usuário inexistente");

        UsuarioPublicoDTO retUser = new UsuarioPublicoDTO();
        retUser.setId(fndUser.get().getId());
        retUser.setLogin(fndUser.get().getLogin());
        retUser.setNome(fndUser.get().getNome());
        retUser.setEmail(fndUser.get().getEmail());
        retUser.setSexo(fndUser.get().getSexo());
        retUser.setCep(fndUser.get().getCep());
        retUser.setIndicacao(fndUser.get().getIndicacao());
        retUser.setDescricao(fndUser.get().getDescricao());
        retUser.setRoles(fndUser.get().getRoles());
        //        retUser.setGraduacoes(fndUser.get().getGraduacoes());

        return retUser;
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Optional<User> fndUser = userRepository.findById(currentUser.getId());
        if (!fndUser.isPresent())
            throw new IllegalArgumentException("Usuário inexistente");

        return fndUser.get();
    }
    public Boolean signUp(SignUpDTO signUpDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (signUpDTO.getLogin().length()<5)
            throw new IllegalArgumentException("Login deve ter no mínimo 5 caracteres");
        if (userRepository.findByLogin(signUpDTO.getLogin()) != null)
            throw new IllegalArgumentException("Login já em uso");
        if (signUpDTO.getNome().equals(""))
            throw new IllegalArgumentException("Nome inválido");
        if (!(signUpDTO.getEmail().contains("@") && signUpDTO.getEmail().contains(".")))
            throw new IllegalArgumentException("Email inválido");
        if (userRepository.findFirstByEmail(signUpDTO.getEmail()) != null)
            throw new IllegalArgumentException("Email já em uso");
        if (signUpDTO.getCpf().equals(""))
            throw new IllegalArgumentException("Preencha o Cpf");
        if (signUpDTO.getCpf().length()<11)
            throw new IllegalArgumentException("CPF deve ter 11 caracteres");
        if (userRepository.findFirstByCpf(signUpDTO.getCpf()) != null)
            throw new IllegalArgumentException("Cpf já cadastrado");
        if (signUpDTO.getPassword().equals(""))
            throw new IllegalArgumentException("Senha inválida");
        if (signUpDTO.getDataNascimento().equals(""))
            throw new IllegalArgumentException("Preencha a data de Nascimento");

        User user = new User();
        user.setLogin(signUpDTO.getLogin());
        user.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        user.setNome(signUpDTO.getNome());
        user.setEmail(signUpDTO.getEmail());
        user.setCpf(signUpDTO.getCpf());
        user.setDataNascimento(signUpDTO.getDataNascimento());

        List<Role> rolePadrao = new ArrayList<>();

        Role role= new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        rolePadrao.add(role);
        user.setRoles(rolePadrao);
        userRepository.save(user);

        return true;
    }
    public Boolean updateUser(User user) {
        if (!user.getId().equals(getCurrentUser().getId()) && !(getCurrentUser().getAuthorities().toString().contains("name=ROLE_ADMIN"))) {
            throw new IllegalArgumentException("Sem permissão");
        }

        Optional<User> fndUser = userRepository.findById(user.getId());
        if (!fndUser.isPresent())
            throw new IllegalArgumentException("Usuário não encontrado");
        if (user.getLogin().equals(""))
            throw new IllegalArgumentException("Login inválido");
        if (!(fndUser.get().getLogin().equals(user.getLogin())) && userRepository.findFirstByLogin(user.getLogin()) != null)
            throw new IllegalArgumentException("Login já existente");
        if (user.getNome().equals(""))
            throw new IllegalArgumentException("Nome inválido");
        if (!(user.getEmail().contains("@") && user.getEmail().contains(".")))
            throw new IllegalArgumentException("Email inválido");
        if (!(fndUser.get().getEmail().equals(user.getEmail())) && userRepository.findFirstByEmail(user.getEmail()) != null)
            throw new IllegalArgumentException("Email já existente");
        if (user.getCep().length() != 8)
            throw new IllegalArgumentException("CEP inválido - 8 digitos");
        if (user.getSexo().equals(""))
            throw new IllegalArgumentException("Sexo inválido");
        if (user.getDataNascimento().equals(""))
            throw new IllegalArgumentException("Preencha todos os campos");
        if (user.getDescricao().equals(""))
            throw new IllegalArgumentException("Descrição inválida");
        if (user.getIndicacao().equals(""))
            throw new IllegalArgumentException("Indicação inválida");

        fndUser.get().setLogin(user.getLogin());
        fndUser.get().setNome(user.getNome());
        fndUser.get().setEmail(user.getEmail());
        fndUser.get().setCep(user.getCep());
        fndUser.get().setSexo(user.getSexo());
        fndUser.get().setDescricao(user.getDescricao());
        fndUser.get().setIndicacao(user.getIndicacao());
        fndUser.get().setDataNascimento(user.getDataNascimento());
        userRepository.save(fndUser.get());

        return true;
    }

    public Boolean forgotPassword(EsqueciSenhaDTO dados) {
        User fndUser = userRepository.findByLogin(dados.getLogin());
        if(fndUser == null)
            throw new IllegalArgumentException("Login não encontrado");
        if(!fndUser.getCpf().equals(dados.getCpf()))
            throw new IllegalArgumentException("Sem permissão");
        if (dados.getPassword().equals(""))
            throw new IllegalArgumentException("Senha inválida");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        fndUser.setPassword(bCryptPasswordEncoder.encode(dados.getPassword()));

        userRepository.save(fndUser);

        return true;
    }
    public Boolean changePassword(TrocarSenhaDTO dados) {
        User currentUser = getCurrentUser();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(dados.getPassword(),currentUser.getPassword()))
            throw new IllegalArgumentException("Senha atual incorreta");
        if (dados.getNewPassword().equals(""))
            throw new IllegalArgumentException("Nova senha inválida");
        currentUser.setPassword(bCryptPasswordEncoder.encode(dados.getNewPassword()));

        userRepository.save(currentUser);

        return true;
    }

    public Boolean deleteUser(Integer userId) {
        Optional<User> fndUser = userRepository.findById(userId);
        if (!fndUser.isPresent())
            throw new IllegalArgumentException("Usuário não encontrado");
        if (!fndUser.get().getId().equals(getCurrentUser().getId()) && !(getCurrentUser().getAuthorities().toString().contains("name=ROLE_ADMIN")))
            throw new IllegalArgumentException("Sem permissão");

        removeUserDepencies(userId);
        userRepository.deleteById(userId);

        return true;
    }
    private void removeUserDepencies(Integer userId) {
        Optional<User> fndUser = userRepository.findById(userId);
        //retirar de vaga (cascade não queria funcionar, esta foi uma solução)
        List<User> usersInVaga;
        for (Vaga vaga: vagaRepository.findByUsers_Id(userId)) {
            usersInVaga = vaga.getUsers();
            usersInVaga.remove(fndUser.get());
            vaga.setUsers(usersInVaga);
            vagaRepository.save(vaga);
    }
        //retirar de graduacao
        for (Graduacao graduacao: graduacaoRepository.findByUserId(userId)) {
                                    graduacaoRepository.delete(graduacao);
        }
        }

        public Boolean emailExists(String email) {
            Optional<User> fndUser = userRepository.findByEmail(email);
            if (!fndUser.isPresent())
                return false;

            return true;
        }
        
}
