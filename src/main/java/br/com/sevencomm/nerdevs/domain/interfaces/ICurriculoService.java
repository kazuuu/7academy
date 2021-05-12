package br.com.sevencomm.nerdevs.domain.interfaces;
import br.com.sevencomm.nerdevs.domain.models.Curriculo;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
public interface ICurriculoService {
String getArquivoByCurriculoId(Integer curriculoId);
List<Curriculo> listMyCurriculos();
Curriculo insertCurriculo(byte[] fileCurriculo);
//    List<Curriculo> listCurriculos();
//    Curriculo updateCurriculo(Curriculo curriculo);
}
