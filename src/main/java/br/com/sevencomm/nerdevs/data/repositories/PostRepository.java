package br.com.sevencomm.nerdevs.data.repositories;

import br.com.sevencomm.nerdevs.domain.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(Integer userId);
    
    List findByTipoId(Integer tipoId);

}
