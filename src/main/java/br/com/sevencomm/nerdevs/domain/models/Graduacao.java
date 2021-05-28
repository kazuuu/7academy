package br.com.sevencomm.nerdevs.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Graduacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @ManyToOne
    //    @JoinColumn(name = "escola_id", referencedColumnName = "id")
    //    private Escola escola;

    private Integer escolaId;
    private String escolaNome;
    private Integer userId;
    private String nivelGraduacao;
    private String nome;
    private Integer semestre;
    private Boolean concluido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFim;
    
}