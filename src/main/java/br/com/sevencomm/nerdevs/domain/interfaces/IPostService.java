package br.com.sevencomm.nerdevs.domain.interfaces;

import br.com.sevencomm.nerdevs.domain.models.Post;
import java.util.List;

public interface IPostService {

    Post getPost(Integer postId);

    List<Post> listPosts();

    void insertPost(Post post);

    Post updatePost(Post post);

    List listPostsByUserId(Integer userId);

    List listPostsByTipo(Integer tipoId);
    
    Boolean deletePost(Integer postId);
    
}
