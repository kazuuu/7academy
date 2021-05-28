package br.com.sevencomm.nerdevs.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
@JsonIgnoreProperties(value = {"arquivo", "user"})
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //    private String nome;
    @Lob
    private byte[] arquivo;

}
