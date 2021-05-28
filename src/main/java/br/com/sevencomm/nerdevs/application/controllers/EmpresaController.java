package br.com.sevencomm.nerdevs.application.controllers;

import br.com.sevencomm.nerdevs.domain.interfaces.IEmpresaService;
import br.com.sevencomm.nerdevs.domain.models.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    final private IEmpresaService empresaService;

    public EmpresaController(IEmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/insert-empresa")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> insertEmpresa(@RequestBody Empresa empresa) {
        try {
            return ResponseEntity.ok(empresaService.insertEmpresa(empresa));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/list-empresas")
    public ResponseEntity<Object> listEmpresas() {
        try {
            return ResponseEntity.ok(empresaService.listEmpresas());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-empresa")
    public ResponseEntity<Object> getEmpresa(@RequestParam("empresaId") Integer empresaId) {
        try {
            return ResponseEntity.ok(empresaService.getEmpresaById(empresaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-empresa")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> updateEmpresa(@RequestBody Empresa empresa) {
        try {
            return ResponseEntity.ok(empresaService.updateEmpresa(empresa));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("delete-empresa")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> deleteEmpresa(@RequestParam("empresaId") Integer empresaId) {
        try {
            return ResponseEntity.ok(empresaService.deleteEmpresa(empresaId));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}