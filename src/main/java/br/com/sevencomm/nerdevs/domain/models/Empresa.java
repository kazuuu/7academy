package br.com.sevencomm.nerdevs.domain.models;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Empresa {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String nome;
private String cnpj;
private String sobre;
}