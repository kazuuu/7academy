package br.com.sevencomm.nerdevs.application.dto;
import br.com.sevencomm.nerdevs.domain.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
@Data
public class UserDTO {
private String login;
private String password;
private String nome;
private String email;
private String cpf;
private String cep;
private String sexo;
private String curriculo;
@JsonFormat(pattern = "dd/MM/yyyy")
LocalDate dataNascimento;
// token jwt
private String token;
public static UserDTO create(User user, String token) {
ModelMapper modelMapper = new ModelMapper();
UserDTO dto = modelMapper.map(user, UserDTO.class);
dto.token = token;
return dto;
}
public String toJson() throws JsonProcessingException {
ObjectMapper m = new ObjectMapper();
return m.writeValueAsString(this);
}
}

