package br.com.sevencomm.nerdevs.data.repositories;

import br.com.sevencomm.nerdevs.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}