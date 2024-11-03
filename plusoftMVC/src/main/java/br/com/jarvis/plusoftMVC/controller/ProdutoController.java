package br.com.jarvis.plusoftMVC.controller;

import br.com.jarvis.plusoftMVC.model.CategoriaProduto;
import br.com.jarvis.plusoftMVC.model.Produto;
import br.com.jarvis.plusoftMVC.model.TamanhoProduto;
import br.com.jarvis.plusoftMVC.repository.ProdutoRepository;
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

import java.util.List;

@Controller
@RequestMapping("produto")
public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Produto produto, Model model){
        model.addAttribute("categoriaProduto", CategoriaProduto.values());
        model.addAttribute("tamanhoProduto", TamanhoProduto.values());
        return "produto/cadastro";
    }

    @GetMapping("listar")
    public String listaProduto(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 8);
        Page<Produto> produtoPage = produtoRepository.findAll(pageable);
        model.addAttribute("produtoPage", produtoPage);
        return "produto/listar";

    }

    @PostMapping("cadastrar")
    public String cadastrar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("categoriaProduto", CategoriaProduto.values());
            model.addAttribute("tamanhoProduto", TamanhoProduto.values());
            return "produto/cadastro";
        }
        produtoRepository.save(produto);
        redirectAttributes.addAttribute("messagem", "Produto Cadastro");
        return "redirect:/produto/listar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id")Long id, Model model){
        model.addAttribute("produto", produtoRepository.findById(id));
        model.addAttribute("categoriaProduto", CategoriaProduto.values());
        model.addAttribute("tamanhoProduto", TamanhoProduto.values());
        return "produto/editar";
    }

    @PostMapping("atualizar")
    public String editar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            model.addAttribute("categoriaProduto", CategoriaProduto.values());
            model.addAttribute("tamanhoProduto", TamanhoProduto.values());
            return "produto/editar";
        }
        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "produto atualizado");
        return "redirect:/produto/listar";
    }



    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        produtoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Produto removido");
        return "redirect:/produto/listar";
    }



}
