package br.com.jarvis.plusoftMVC.repository;

import br.com.jarvis.plusoftMVC.model.Cliente;
import br.com.jarvis.plusoftMVC.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByUsername(String username);
    Page<Funcionario> findAll(Pageable pageable);

}
