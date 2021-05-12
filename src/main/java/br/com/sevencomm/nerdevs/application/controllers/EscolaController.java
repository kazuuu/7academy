package br.com.sevencomm.nerdevs.application.controllers;
import br.com.sevencomm.nerdevs.domain.interfaces.IEscolaService;
import org.springframework.http.ResponseEntity;
import br.com.sevencomm.nerdevs.domain.models.Escola;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/escola")
public class EscolaController {
final private IEscolaService escolaService;
public EscolaController(IEscolaService _escolaService) {
this.escolaService = _escolaService;
}
@PostMapping("/insert-escola")
@Secured({ "ROLE_ADMIN" })
public ResponseEntity<Object> insertEscola(@RequestBody Escola escola) {
try {
return ResponseEntity.ok(escolaService.insertEscola(escola));
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
@GetMapping("/list-escolas")
public ResponseEntity<Object> listEscolas() {
try {
return ResponseEntity.ok(escolaService.listEscolas());
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
@GetMapping("/get-escola")
public ResponseEntity<Object> getEscola(@RequestParam("escolaId") Integer escolaId) {
try {
return ResponseEntity.ok(escolaService.getEscolaById(escolaId));
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
@PostMapping("/update-escola")
@Secured({ "ROLE_ADMIN" })
public ResponseEntity<Object> updateEscola(@RequestBody Escola escola) {
try {
return ResponseEntity.ok(escolaService.updateEscola(escola));
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
@DeleteMapping("delete-escola")
@Secured({ "ROLE_ADMIN" })
public ResponseEntity<Object> deleteEscola(@RequestParam("escolaId") Integer escolaId) {
try {
return ResponseEntity.ok(escolaService.deleteEscola(escolaId));
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
}