package br.com.sevencomm.nerdevs.application.controllers;

import br.com.sevencomm.nerdevs.application.dto.SignUpDTO;
import br.com.sevencomm.nerdevs.application.dto.EsqueciSenhaDTO;
import br.com.sevencomm.nerdevs.application.dto.TrocarSenhaDTO;
import br.com.sevencomm.nerdevs.domain.interfaces.IUserService;
import br.com.sevencomm.nerdevs.domain.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService _userService) {
    userService = _userService;
    }

    @GetMapping("/list-users")
    public ResponseEntity<Object> listUsers() {
        try {
            return ResponseEntity.ok(userService.listUsers());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam("userId") Integer userId) {
        try {
            return ResponseEntity.ok(userService.getUser(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/email-exists")
    public ResponseEntity<Object> emailExists(@RequestBody String email) {
        try {
            return ResponseEntity.ok(userService.emailExists(email));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-current-user")
    public ResponseEntity<Object> getCurrentUser() {
        try {
            return ResponseEntity.ok(userService.getCurrentUser());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@Validated @RequestBody SignUpDTO signUpDTO) {
        try {
            return ResponseEntity.ok(userService.signUp(signUpDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-user")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@Validated @RequestBody EsqueciSenhaDTO dados) {
        try {
            return ResponseEntity.ok(userService.forgotPassword(dados));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@Validated @RequestBody TrocarSenhaDTO dados) {
        try {
            return ResponseEntity.ok(userService.changePassword(dados));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @DeleteMapping("delete-user")
    public ResponseEntity<Object> deleteUser(@RequestParam("userId") Integer userId) {
        try {
            return ResponseEntity.ok(userService.deleteUser(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
}