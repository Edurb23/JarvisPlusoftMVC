package br.com.jarvis.plusoftMVC.service;

import br.com.jarvis.plusoftMVC.model.Funcionario;
import br.com.jarvis.plusoftMVC.model.Role;
import br.com.jarvis.plusoftMVC.repository.FuncionarioRepository;
import br.com.jarvis.plusoftMVC.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FuncionarioService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Autowired
    private RoleRepository roleRepository;

    public void saveFunc(String username, String password, List<String> roles){
        Set<Role> listaRoles = new HashSet<>();
        for (String nome : roles){
            Role role = roleRepository.findByName(nome);
            if (role != null) listaRoles.add(role);
        }
        Funcionario funcionario  = new Funcionario(username, password, listaRoles);
        funcionarioRepository.save(funcionario);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByUsername(username);
        if (funcionario == null) {
            throw new UsernameNotFoundException("Funcionario não encontrado");
        }
        Set<SimpleGrantedAuthority> authorities = funcionario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(
                funcionario.getUsername(),
                funcionario.getPassword(),
                authorities);


    }
}
