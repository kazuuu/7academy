package br.com.sevencomm.nerdevs.application.seeders;

import br.com.sevencomm.nerdevs.data.repositories.EmpresaRepository;
import br.com.sevencomm.nerdevs.data.repositories.RoleRepository;
import br.com.sevencomm.nerdevs.data.repositories.UserRepository;
import br.com.sevencomm.nerdevs.data.repositories.VagaRepository;
import br.com.sevencomm.nerdevs.domain.models.Empresa;
import br.com.sevencomm.nerdevs.domain.models.Role;
import br.com.sevencomm.nerdevs.domain.models.User;
import br.com.sevencomm.nerdevs.domain.models.Vaga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// https://github.com/SeunMatt/spring-blog/blob/master/src/main/java/com/smatt/seeders/DatabaseSeeder.java
@Component
public class UserSeeder {
    private static UserRepository userRepository;
    private static RoleRepository roleRepository;
    private static EmpresaRepository empresaRepository;
    private static VagaRepository vagaRepository;

    @Autowired
    public UserSeeder(UserRepository _userRepository,
                    RoleRepository _roleRepository,
                    EmpresaRepository _empresaRepository,
                    VagaRepository _vagaRepository) {
        this.userRepository = _userRepository;
        this.roleRepository = _roleRepository;
        this.empresaRepository = _empresaRepository;
        this.vagaRepository = _vagaRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (userRepository.findAll().isEmpty()) {
        seedUserTable();
        }
    }

    private void seedUserTable() {
        User user = new User();

        user.setLogin("admin");
        user.setNome("Administrador");
        user.setEmail("administrador@gmail.com");
        user.setCpf("12312312312");
        user.setCep("08090765");
        user.setSexo("Masculino");
        user.setDataNascimento(LocalDate.now());
        user.setDescricao(null);
        user.setIndicacao(null);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode("123"));

        Role roleUser = new Role();
        roleUser.setId(1);
        roleUser.setName("ROLE_USER");

        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setId(2);
        roleAdmin.setName("ROLE_ADMIN");

        roleRepository.save(roleAdmin);

        List<Role> listRole = new ArrayList<Role>();
        listRole.add(roleAdmin);
        user.setRoles(listRole);

        userRepository.save(user);
        System.out.println("Seed User Done");

        Empresa empresa = new Empresa();
        empresa.setCnpj("89613711000145");
        empresa.setNome("7COMm Informática");
        empresa.setSobre("Integradora e Consultoria em TI");

        empresa = empresaRepository.save(empresa);

        Vaga vaga = new Vaga();
        vaga.setEmpresaId(empresa.getId());
        vaga.setTitulo("Programa de Trainee Fullstack ++");
        vaga.setDescricao("Venha participar do treinamento intensivo da 7COMm para desenvolvedores. Em tres anos vc será um avião");
        vaga.setSalario(1000.00);
        vaga.setQuantidadeVagas(10);
        vaga.setRequisitos("Computador e vontade de aprender");
        vaga.setTurno("Diurno");

        vaga = vagaRepository.save(vaga);
    }
    
}

