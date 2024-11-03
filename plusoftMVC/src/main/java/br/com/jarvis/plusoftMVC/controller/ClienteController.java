package br.com.jarvis.plusoftMVC.controller;

import br.com.jarvis.plusoftMVC.model.Cliente;
import br.com.jarvis.plusoftMVC.repository.ClienteRepository;
import br.com.jarvis.plusoftMVC.repository.TelefoneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private TelefoneRepository telefoneRepository;


    @GetMapping("cadastar")
    public String cadastrar(Cliente cliente, Model model){
       return "cliente/cadastro";
    }

    @GetMapping("listar")
    public String listaCliente(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 8);
        Page<Cliente> clientePage = clienteRepositorio.findAll(pageable);
        model.addAttribute("clientePage", clientePage);
        return "cliente/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Álbum inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "cliente/editar";
    }



    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            return "cliente/cadastro";
        }
        clienteRepositorio.save(cliente);
        redirectAttributes.addAttribute("mensagem", "Cliente Cadastrado");
        return "redirect:/cliente/listar";
    }


    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Cliente cliente, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "cliente/editar";
        }
        clienteRepositorio.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente editado");
        return "redirect:/cliente/listar";
    }

    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        clienteRepositorio.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente removido");
        return "redirect:/cliente/listar";
    }






}
