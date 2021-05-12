package br.com.sevencomm.nerdevs.domain.services;
import br.com.sevencomm.nerdevs.data.repositories.PostRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.IPostService;
import br.com.sevencomm.nerdevs.domain.models.Post;
import java.util.List;
import br.com.sevencomm.nerdevs.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class PostServiceImpl implements IPostService {
private PostRepository postRepository;
private UserRepository userRepository;
public PostServiceImpl(PostRepository _postRepository, UserRepository _userRepository) {
postRepository = _postRepository;
userRepository = _userRepository;
}
public List<Post> listPosts() {
return postRepository.findAll();
}
public List listPostsByUserId(Integer userId) {
Optional<User> user = userRepository.findById(userId);
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
return postRepository.findByUserId(userId);
}
public List listPostsByTipo(Integer tipoId) {
return postRepository.findByTipoId(tipoId);
}
public Post getPost(Integer postId) {
Optional<Post> feed = postRepository.findById(postId);
if(!feed.isPresent())
throw new IllegalArgumentException("Post inexistente");
return feed.get();
}
public void insertPost(Post post) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
if (!post.getUserId().equals(currentUser.getId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
if (post.getUserId() == null)
throw new IllegalArgumentException("Usuário inválido");
Optional<User> user = userRepository.findById(post.getUserId());
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
if (post.getTipoId() == null)
throw new IllegalArgumentException("Tipo inválido");
if (post.getTitulo().equals(""))
throw new IllegalArgumentException("Título inválido");
if (post.getDescricao().equals(""))
throw new IllegalArgumentException("Descrição inválida");
postRepository.save(post);
}
public Post updatePost(Post post) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
Optional<Post> fndPost = postRepository.findById(post.getId());
if (!fndPost.isPresent())
throw new IllegalArgumentException("Post não encontrado");
if (!fndPost.get().getUserId().equals(currentUser.getId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
if (post.getUserId() == null)
throw new IllegalArgumentException("Usuário inválido");
Optional<User> user = userRepository.findById(post.getUserId());
if (!user.isPresent())
throw new IllegalArgumentException("Usuário inexistente");
if (post.getTipoId() == null)
throw new IllegalArgumentException("Tipo inválido");
if (post.getTitulo().equals(""))
throw new IllegalArgumentException("Título inválido");
if (post.getDescricao().equals(""))
throw new IllegalArgumentException("Descrição inválida");
return postRepository.save(post);
}
public Boolean deletePost(Integer postId) {
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User currentUser = (User) authentication.getPrincipal();
Optional<Post> post = postRepository.findById(postId);
if (!post.isPresent())
throw new IllegalArgumentException("Post inexistente");
if (!post.get().getUserId().equals(currentUser.getId()) && !(currentUser.getAuthorities().toString().contains("name=ROLE_ADMIN")))
throw new IllegalArgumentException("Sem permissão");
postRepository.deleteById(postId);
return true;
}
}
