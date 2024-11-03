package br.com.jarvis.plusoftMVC.config;

import br.com.jarvis.plusoftMVC.model.Funcionario;
import br.com.jarvis.plusoftMVC.model.Role;
import br.com.jarvis.plusoftMVC.repository.FuncionarioRepository;
import br.com.jarvis.plusoftMVC.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FuncionarioRepository funcionarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (funcionarioRepository.count() == 0) {

                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                adminRole.setLabel("Admin");
                roleRepository.save(adminRole);

                Role funcRole = new Role();
                funcRole.setName("ROLE_FUNC");
                funcRole.setLabel("Func");
                roleRepository.save(funcRole);


                Funcionario admin = new Funcionario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(adminRole));
                funcionarioRepository.save(admin);

                Funcionario user = new Funcionario();
                user.setUsername("user1");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(funcRole));
                funcionarioRepository.save(user);

                System.out.println("Roles e Funcionarios inicializados no banco de dados.");
            } else {
                System.out.println("Funcionarios já existentes. Nenhuma ação necessária.");
            }
        };
    }
}