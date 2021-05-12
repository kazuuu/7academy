package br.com.sevencomm.nerdevs.data.repositories;
import br.com.sevencomm.nerdevs.domain.models.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface CurriculoRepository extends JpaRepository<Curriculo, Integer> {
List<Curriculo> findByUser_id(Integer userId);
}
