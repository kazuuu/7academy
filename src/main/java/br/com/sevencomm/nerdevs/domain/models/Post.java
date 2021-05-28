package br.com.sevencomm.nerdevs.domain.models;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Post {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @NotNull
    private Integer tipoId;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;
    
    private Integer vagaId;
    private Integer empresaId;
    private Integer escolaId;
}
