package br.com.sevencomm.nerdevs.domain.services;

import br.com.sevencomm.nerdevs.data.repositories.CurriculoRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.domain.interfaces.ICurriculoService;
import br.com.sevencomm.nerdevs.domain.models.Curriculo;
import br.com.sevencomm.nerdevs.domain.models.Graduacao;
import br.com.sevencomm.nerdevs.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CurriculoServiceImpl implements ICurriculoService {

    private final CurriculoRepository curriculoRepository;
    private final UserRepository userRepository;

    public CurriculoServiceImpl(CurriculoRepository curriculoRepository, UserRepository _userRepository) {
        this.curriculoRepository = curriculoRepository;
        this.userRepository = _userRepository;
    }

    @Override
    public List<Curriculo> listMyCurriculos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        
        Optional<User> user = userRepository.findById(currentUser.getId());

        if (!user.isPresent())
            throw new IllegalArgumentException("Usuário inexistente");
        return curriculoRepository.findByUser_id(currentUser.getId());
    }

    @Override
    public String getArquivoByCurriculoId(Integer curriculoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Optional<User> user = userRepository.findById(currentUser.getId());

        if (!user.isPresent())
            throw new IllegalArgumentException("Usuário inexistente");

        Optional<Curriculo> curriculo = curriculoRepository.findById(curriculoId);

        if(curriculo.get().getUser().getId() != currentUser.getId())
            throw new IllegalArgumentException("Sem Permissão");

        return new String(Base64.getEncoder().encode(curriculoRepository.findById(curriculoId).get().getArquivo()));
    }

    @Override
    public Curriculo insertCurriculo(byte[] fileCurriculo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Optional<User> user = userRepository.findById(currentUser.getId());

        if(!user.isPresent())
            throw new IllegalArgumentException(("Usuário inexistente"));

        if (fileCurriculo.equals(null))
            throw new IllegalArgumentException("Curriculo Inválido");

        Curriculo curriculo = new Curriculo();
        curriculo.setArquivo(fileCurriculo);
        curriculo.setUser(currentUser);
        //        curriculo.getArquivo().getOriginalFilename();
        
        return curriculoRepository.save(curriculo);
    }

}
