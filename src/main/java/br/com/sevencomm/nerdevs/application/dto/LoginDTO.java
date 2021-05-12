package br.com.sevencomm.nerdevs.application.dto;
import br.com.sevencomm.nerdevs.domain.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
@Data
public class LoginDTO {
private MeDTO me;
private String token;
public static LoginDTO create(User user, String token) {
LoginDTO dto = new LoginDTO();
dto.token = token;
dto.me = new MeDTO(user);
return dto;
}
public String toJson() throws JsonProcessingException {
ObjectMapper m = new ObjectMapper();
return m.writeValueAsString(this);
}
}
