package br.com.sevencomm.nerdevs.domain.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@JsonIgnoreProperties(value = {"users"})
public class Vaga {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "user_vagas",
joinColumns = @JoinColumn(name = "vaga_id", referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
private List<User> users;
private String titulo;
private String descricao;
private String beneficios;
private String requisitos;
private Integer empresaId;
private Integer quantidadeVagas;
private Double salario;
private String turno;
@JsonFormat(pattern = "dd/MM/yyyy")
private Date datetimeCreated;
@JsonFormat(pattern = "dd/MM/yyyy")
private Date datetimeUpdated;
}
