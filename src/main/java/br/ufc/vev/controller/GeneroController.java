package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.GeneroService;

@Controller
@RequestMapping(path= "/genero/")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		
		try {
			ModelAndView model = new ModelAndView("genero");
			List<Genero> generos = generoService.getAllGenero();
			
			model.addObject("generos", generos);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaGenero(@RequestParam String nomeGenero) {
		try {
			if (this.validaGenero(nomeGenero)) {
				ModelAndView model = new ModelAndView("genero");
				
				Genero genero = new Genero();
				genero.setNome(nomeGenero);
				
				Genero generoRetorno = generoService.salvarGenero(genero);
				
				model.addObject("generoRetorno", generoRetorno);
				
				List<Genero> generos = generoService.getAllGenero();
				
				model.addObject("generos", generos);
				
				return model;
				
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public Genero buscaGenero(int id) {
		try {
			if (validaIdGenero(id)) {
				return generoService.buscarGenero(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@RequestMapping(path="/excluir", method = RequestMethod.DELETE)
	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
		try {
			ModelAndView model = new ModelAndView("genero");
			Genero genero = new Genero();
			
			if (validaIdGenero(id)) {
				genero = generoService.buscarGenero(id);
				generoService.excluirGenero(genero);
				
				List<Genero> generos = generoService.getAllGenero();
				model.addObject("generos", generos);
				
				return model;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Genero> getAllGenero() {		
		return generoService.getAllGenero();
	}

	public boolean atualizaGenero(Genero genero) {
		try {
			if (buscaGenero(genero.getId()) != null && 
					validaGenero(genero.getNome()) &&
					validaIdGenero(genero.getId())) {
				generoService.atualizaGenero(genero);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validaGenero(String nome) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		}
		return true;
	}
	
	public boolean validaIdGenero(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro, ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro, ID não pode ser negativo");
		}
		return true;
	}
	
//	public boolean vincularGeneroAoFilme(Genero genero, Filme filme) {
//		try {
//			
//		}catch(Exception e) {
//			e.printStackTrace(e);
//		}
//	}


}
