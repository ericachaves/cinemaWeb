package com.erica.cinema.controller;

import com.erica.cinema.model.Analise;
import com.erica.cinema.model.Filme;
import com.erica.cinema.model.Preferencia;
import com.erica.cinema.service.AnaliseService;
import com.erica.cinema.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @Autowired
    AnaliseService analiseService;

    @GetMapping("/")
    public String inicio(@CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
         model.addAttribute("css", tema);
        return "index";
    }

    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {

        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setDomain("localhost"); //disponível apenas no domínio "localhost" 
        cookiePrefEstilo.setHttpOnly(true); //acessível apenas por HTTP, JS não 
        cookiePrefEstilo.setMaxAge(86400); //1 dia 
        
        response.addCookie(cookiePrefEstilo);
        return new ModelAndView("redirect:/"); //"index"; 
    }

    @GetMapping("/inserir-filme")
    public String cadastroForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @GetMapping("/listagem")
    public String listaForm(Model model) {
        model.addAttribute("lista", filmeService.listarTodos());
        return "lista";
    }

    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Filme filme, Model model) {

        if (filme.getId() != null) {
            filmeService.atualizar(filme.getId(), filme);
        } else {
            filmeService.criar(filme);
        }

        return "redirect:/listagem";
    }

    @PostMapping("/gravar-analise")
    public String gravarAnaliseUsuario(@ModelAttribute Filme filme, @ModelAttribute Analise analise, Model model) {
        analise.setFilme(filme);
        analiseService.criar(analise);
        return "redirect:/listagem";
    }

    @GetMapping("/exibir")
    public String mostraDetalhesFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);

        Filme registroEncontrado = new Filme();
        registroEncontrado = filmeService.buscarPorId(idFilme);

        List<Analise> analisesEncontradas = new ArrayList<>();
        analisesEncontradas = analiseService.listar(idFilme);

        model.addAttribute("filme", registroEncontrado);
        model.addAttribute("analise", new Analise());
        model.addAttribute("analises", analisesEncontradas);
        return "exibir";
    }

    @GetMapping("/excluir")
    public String excluirFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        analiseService.excluirTodasAnalisesPorFilme(idFilme);
        filmeService.excluir(idFilme);
        return "redirect:/listagem";
    }

    @GetMapping("/alterar")
    public String alterarFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        Filme filmeEncontrado = filmeService.buscarPorId(idFilme);
        model.addAttribute("filme", filmeEncontrado);
        return "alterar";
    }

    @GetMapping("/excluirAnalise")
    public String excluirAnalise(Model model, @RequestParam String id) {
        Integer idAnalise = Integer.parseInt(id);
        analiseService.excluirAnalise(idAnalise);
        return "redirect:/listagem";
    }
}
