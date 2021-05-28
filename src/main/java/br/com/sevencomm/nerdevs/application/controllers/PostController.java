package br.com.sevencomm.nerdevs.application.controllers;

import br.com.sevencomm.nerdevs.domain.interfaces.IPostService;
import br.com.sevencomm.nerdevs.domain.models.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService _postService) {
        postService = _postService;
    }

    @GetMapping("/list-posts")
    public ResponseEntity<Object> listPosts() {
        try {
            return ResponseEntity.ok(postService.listPosts());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-post")
    public ResponseEntity<Object> getPost(@RequestParam("postId") Integer postId) {
        try {
            return ResponseEntity.ok(postService.getPost(postId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-posts-by-user-id")
    public ResponseEntity<Object> listPostsByUserId(@RequestParam("userId") Integer userId) {
        try {
            return ResponseEntity.ok(postService.listPostsByUserId(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-posts-by-tipo")
    public ResponseEntity<Object> listPostsByTipo(@RequestParam("tipoId") Integer tipoId) {
        try {
            return ResponseEntity.ok(postService.listPostsByTipo(tipoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/insert-post")
    public ResponseEntity<Object> insertPost(@RequestBody Post post) {
        try {
            postService.insertPost(post);
            return ResponseEntity.created(null).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-post")
    public ResponseEntity<Object> updatePost(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.updatePost(post));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("delete-post")
    public ResponseEntity<Object> deletePost(@RequestParam("postId") Integer postId) {
        try {
            return ResponseEntity.ok(postService.deletePost(postId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}