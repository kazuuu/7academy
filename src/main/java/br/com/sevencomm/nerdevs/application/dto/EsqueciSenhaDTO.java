package br.com.sevencomm.nerdevs.application.dto;

import lombok.Data;

@Data
public class EsqueciSenhaDTO {
    private String login;
    private String cpf;
    private String password;
    
}