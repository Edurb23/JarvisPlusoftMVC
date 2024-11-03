package br.com.jarvis.plusoftMVC.repository;

import br.com.jarvis.plusoftMVC.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String nome);

}