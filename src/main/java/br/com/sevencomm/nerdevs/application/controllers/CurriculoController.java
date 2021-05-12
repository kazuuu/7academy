package br.com.sevencomm.nerdevs.application.controllers;
import br.com.sevencomm.nerdevs.domain.interfaces.ICurriculoService;
import br.com.sevencomm.nerdevs.domain.interfaces.IUserService;
import br.com.sevencomm.nerdevs.domain.models.Curriculo;
import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.Convert;
import java.sql.Blob;
import java.util.Base64;
@RestController
@RequestMapping("/curriculo")
public class CurriculoController {
private final ICurriculoService curriculoService;
public CurriculoController(ICurriculoService _curriculoService) {
curriculoService = _curriculoService;
}
@GetMapping("/list-my-curriculos")
public ResponseEntity<Object> listMyCurriculos() {
try {
return ResponseEntity.ok(curriculoService.listMyCurriculos());
} catch (Exception ex) {
return ResponseEntity.notFound().build();
}
}
@GetMapping("/get-arquivo-by-curriculo-id")
public ResponseEntity<Object> getCurriculo(@RequestParam("curriculoId") Integer curriculoId) {
try {
return ResponseEntity.ok(curriculoService.getArquivoByCurriculoId(curriculoId));
} catch (Exception ex) {
return ResponseEntity.badRequest().build();
}
}
@PostMapping("insert-curriculo")
public ResponseEntity<Object> insertCurriculo(@RequestBody byte[] fileCurriculo) {
try {
curriculoService.insertCurriculo(fileCurriculo);
return ResponseEntity.created(null).build();
} catch (Exception ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
}