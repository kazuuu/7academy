package br.com.sevencomm.nerdevs.domain.interfaces;
import br.com.sevencomm.nerdevs.application.dto.*;
import br.com.sevencomm.nerdevs.domain.models.User;
import java.util.List;
public interface IUserService {
UsuarioPublicoDTO getUser(Integer userId);
UsuarioPublicoDTO getUserByEmail(String email);
User getCurrentUser();
Boolean updateUser(User user);
Boolean signUp(SignUpDTO signUpDTO);
Boolean forgotPassword(EsqueciSenhaDTO dados);
List<UsuarioPublicoDTO> listUsers();
Boolean deleteUser(Integer userId);
Boolean changePassword(TrocarSenhaDTO dados);
Boolean emailExists(String email);
}
