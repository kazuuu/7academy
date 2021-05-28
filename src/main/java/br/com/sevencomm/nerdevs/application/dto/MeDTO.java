package br.com.sevencomm.nerdevs.application.dto;

import br.com.sevencomm.nerdevs.domain.models.Role;
import br.com.sevencomm.nerdevs.domain.models.User;
import lombok.Data;
import java.util.List;

@Data
public class MeDTO {

    public MeDTO() {
    }

    public MeDTO(User user) {
        id = user.getId();
        login = user.getLogin();
        nome = user.getNome();
        email= user.getEmail();
        sexo = user.getSexo();
        indicacao = user.getIndicacao();
        descricao = getDescricao();
        roles = user.getRoles();
    }
    
    private Integer id;
    private String login;
    private String nome;
    private String email;
    private String sexo;
    private String descricao;
    private String indicacao;
    private List<Role> roles;

}
