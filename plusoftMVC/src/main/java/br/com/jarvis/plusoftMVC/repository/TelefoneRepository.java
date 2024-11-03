package br.com.jarvis.plusoftMVC.repository;

import br.com.jarvis.plusoftMVC.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
