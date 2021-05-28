package br.com.sevencomm.nerdevs.application.controllers;

import br.com.sevencomm.nerdevs.domain.interfaces.IGraduacaoService;
import br.com.sevencomm.nerdevs.domain.models.Graduacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graduacao")
public class GraduacaoController {
    private final IGraduacaoService graduacaoService;

    public GraduacaoController(IGraduacaoService _graduacaoService) { graduacaoService = _graduacaoService; }

    @GetMapping("/list-graduacoes")
    public ResponseEntity<Object> listGraduacoes() {
        try {
            return ResponseEntity.ok(graduacaoService.listGraduacoes());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("get-graduacao")
    public ResponseEntity<Object> getGraduacao(@RequestParam("graduacaoId") Integer graduacaoId) {
        try {
            return ResponseEntity.ok(graduacaoService.getGraduacao(graduacaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("list-graduacoes-by-user-id")
    public ResponseEntity<Object> listGraduacoesByUserId(@RequestParam("userId") Integer userId) {
        try {
            return ResponseEntity.ok(graduacaoService.listGraduacoesByUserId(userId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("insert-graduacao")
    public ResponseEntity<Object> insertGraduacao(@RequestBody Graduacao graduacao) {
        try {
            graduacaoService.insertGraduacao(graduacao);
            return ResponseEntity.created(null).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("update-graduacao")
    public ResponseEntity<Object> updateGraduacao(@RequestBody Graduacao graduacao) {
        try {
            return ResponseEntity.ok(graduacaoService.updateGraduacao(graduacao));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("delete-graduacao")
    public ResponseEntity<Object> deleteGraduacao(@RequestParam("graduacaoId") Integer graduacaoId) {
        try {
            return ResponseEntity.ok(graduacaoService.deleteGraduacao(graduacaoId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
}