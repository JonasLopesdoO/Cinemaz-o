package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.service.AtorService;


@Controller
@RequestMapping(path= "/ator")
public class AtorController {
	
	@Autowired
	private AtorService atorService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("ator");
		try {
			List<Ator> atores = getAllAtor();
			
			model.addObject("atores", atores);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioAtor() {
		ModelAndView model = new ModelAndView("formulario-ator");
		model.addObject("ator", new Ator());
		
		return model;
	}

	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaAtor(Ator ator) {
		ModelAndView model = new ModelAndView("ator");
		atorService.salvarAtor(ator);
		model.addObject("atorRetorno", ator);
		return index();
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaAtor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("ator");
		try {	
			if(existsByIdAtor(id)) {
				Ator ator = new Ator();			
				ator = atorService.buscarAtor(id);			
				model.addObject("atorRetorno", ator);
			}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiAtor(@PathVariable("id") Integer id) {		
		try {
			Ator ator = new Ator();
			if (existsByIdAtor(id)) {
				ator = atorService.buscarAtor(id);
				atorService.excluirAtor(ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}

	public List<Ator> getAllAtor() {		
		return atorService.getAllAtor();
	}
	
	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaAtor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-ator");

		try {
			if (existsByIdAtor(id)) {
				Ator ator = atorService.buscarAtor(id);
				
				model.addObject("ator", ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
	}
	
	public boolean existsByIdAtor(int id) {
		return atorService.buscaAtor(id);
	}
}
