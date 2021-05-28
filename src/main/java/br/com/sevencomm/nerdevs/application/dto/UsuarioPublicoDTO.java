package br.com.sevencomm.nerdevs.application.dto;

import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import br.com.sevencomm.nerdevs.domain.models.Role;
import br.com.sevencomm.nerdevs.domain.models.User;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioPublicoDTO {

    public UsuarioPublicoDTO() {
    }

    public UsuarioPublicoDTO(User user) {
        id = user.getId();
        login = user.getLogin();
        nome = user.getNome();
        email= user.getEmail();
        sexo = user.getSexo();
        cep = user.getCep();
        descricao = user.getDescricao();
        indicacao = user.getIndicacao();
        roles = user.getRoles();
        //        graduacoes = user.getGraduacoes();
        dataNascimento = user.getDataNascimento();
    }
    
    private Integer id;
    private String login;
    private String nome;
    private String email;
    private String sexo;
    private String cep;
    private String descricao;
    private String indicacao;
    private List<Role> roles;
    private List<Graduacao> graduacoes;
    LocalDate dataNascimento;

}
