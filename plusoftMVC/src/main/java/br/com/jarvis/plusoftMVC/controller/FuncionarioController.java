package br.com.jarvis.plusoftMVC.controller;

import br.com.jarvis.plusoftMVC.dto.FuncForm;
import br.com.jarvis.plusoftMVC.model.Funcionario;
import br.com.jarvis.plusoftMVC.model.Role;
import br.com.jarvis.plusoftMVC.repository.FuncionarioRepository;
import br.com.jarvis.plusoftMVC.repository.RoleRepository;
import br.com.jarvis.plusoftMVC.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("funcForm", new FuncForm());
        model.addAttribute("roles",roleRepository.findAll());
        return "funcionario/register";
    }

    @GetMapping("listar")
    public String listaFuncionario(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Funcionario> funcionarioPage = funcionarioRepository.findAll(pageable);

        model.addAttribute("funcionarioPage", funcionarioPage);
        return "funcionario/listar";
    }



    @PostMapping("/register")
    public String registerFunc(@ModelAttribute("funcForm") FuncForm funcForm){
        funcionarioService.saveFunc( funcForm.getUsername(),
                passwordEncoder.encode(funcForm.getPassword()), funcForm.getRoles());
        return "redirect:/funcionario/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        funcionarioRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario removido");
        return "redirect:/funcionario/listar";
    }








}
