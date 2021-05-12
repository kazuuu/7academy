package br.com.sevencomm.nerdevs.application.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
@Data
public class SignUpDTO {
private String login;
private String password;
private String nome;
private String email;
private String cpf;
private String sexo;
LocalDate dataNascimento;
}