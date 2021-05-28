package br.com.sevencomm.nerdevs.application.controllers;

import br.com.sevencomm.nerdevs.application.dto.IsJoinedDTO;
import br.com.sevencomm.nerdevs.application.dto.TrocarSenhaDTO;
import br.com.sevencomm.nerdevs.domain.interfaces.IVagaService;
import br.com.sevencomm.nerdevs.domain.models.Vaga;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private final IVagaService vagaService;

    public VagaController(IVagaService _vagaService) {
        vagaService = _vagaService;
    }

    @GetMapping("/get-vaga")
    public ResponseEntity<Object> getVaga(@RequestParam("vagaId") Integer vagaId) {
        try {
            return ResponseEntity.ok(vagaService.getVaga(vagaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/insert-vaga")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> insertVaga(@Validated @RequestBody Vaga vaga) {
        try {
            return ResponseEntity.ok(vagaService.insertVaga(vaga));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/join-vaga")
    public ResponseEntity<Object> joinVaga(@RequestParam("vagaId") Integer vagaId) {
        try {
            return ResponseEntity.ok(vagaService.joinVaga(vagaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/is-joined")
    public ResponseEntity<Object> isJoined(@Validated @RequestBody IsJoinedDTO dados){
        try {
            return ResponseEntity.ok(vagaService.isJoined(dados));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-vaga")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> updateVaga(@RequestBody Vaga vaga) {
        try {
            return ResponseEntity.ok(vagaService.updateVaga(vaga));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("list-vagas")
    public ResponseEntity<Object> listVagas() {
        try {
            List<Vaga> vagas = vagaService.listVagas();
            return vagas.isEmpty()?
            ResponseEntity.noContent().build():
            ResponseEntity.ok().body(vagas);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("list-vagas-by-salario-range")
    public ResponseEntity<Object> listVagasBySalarioRange(@RequestParam("Min") Integer min, @RequestParam("Max") Integer max) {
        try {
            return ResponseEntity.ok(vagaService.listVagasBySalarioRange(min, max));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("delete-vaga")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> deleteVaga(@RequestParam("vagaId") Integer vagaId) {
        try {
            return ResponseEntity.ok(vagaService.deleteVaga(vagaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}