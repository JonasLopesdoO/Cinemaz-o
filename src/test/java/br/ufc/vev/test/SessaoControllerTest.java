package br.ufc.vev.test;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.repositorio.SessaoRepositorio;



public class SessaoControllerTest {
	
<<<<<<< HEAD
=======
	private SessaoRepositorio sessaoRepositorio;
	
	private FilmeController filmeControlMock;
	private SalaController salaControlMock;

>>>>>>> parent of 8369a8e... Testes
	@Autowired
	private SessaoController sessaoController;
	
	@Test
	public void atualizarUmaSessaoExistente() {

		Filme filme       = Mock.mockFilme().buscarFilmeId(1);
		Sala sala         = Mock.mockSala().buscarSalaId(1);
		LocalTime horario = LocalTime.of(20,30);
		LocalDate inicio  = LocalDate.of(2018, 05, 01);
		LocalDate fim     = LocalDate.of(2018, 05, 30);
		
		Filme filme2       = Mock.mockFilme().buscarFilmeId(2);
		Sala sala2         = Mock.mockSala().buscarSalaId(2);
		LocalTime horario2 = LocalTime.of(20,35);
		LocalDate inicio2  = LocalDate.of(2018, 05, 02);
		LocalDate fim2     = LocalDate.of(2018, 05, 31);
		
		Sessao sessao = new Sessao(filme, sala, horario, inicio, fim);
		
		Sessao sessaoRecebida = (Sessao) sessaoController.addSessao(filme, sala, horario, inicio, fim).
								getModel().get("sessao");
		
		
		Sessao sessaoAtualizada = (Sessao) sessaoController.atualizarSessao(sessao.getId(),
								  filme2, sala2, horario2, inicio2, fim2).getModel().get("sessao");
		
		System.out.println(sessaoAtualizada.toString());
		assertNotNull(sessaoAtualizada);
		
	}
	
	@Test
	public void adicionarUmaSessaoCorretamete() {

		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalTime horario = LocalTime.of(20, 00);
		LocalDate dataInicio = LocalDate.of(2018, 05, 15);
		LocalDate dataFim = LocalDate.of(2018, 05, 30);
		
		Sessao sessao = (Sessao) sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim).getModel().get("sessao");

		assertNotNull(sessao);
		
	}
	
	@Test
	public void adicionarUmaSessaoComHorarioNulo() {

		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalDate dataInicio = LocalDate.of(2018, 05, 15);
		LocalDate dataFim = LocalDate.of(2018, 05, 30);
		
		sessaoController.addSessao(filme1, sala1, null, dataInicio, dataFim);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
		
	}
	
	@Test
	public void adicionarUmaSessaoComDataInicioNula() {

		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalTime horario = LocalTime.of(20, 00);
		LocalDate dataInicio = null;
		LocalDate dataFim = LocalDate.of(2018, 05, 30);
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
		
	}
	
	@Test
	public void adicionarUmaSessaoComDataFimNula() {

		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalTime horario = LocalTime.of(20, 00);
		LocalDate dataInicio = LocalDate.of(2018, 05, 15);
		LocalDate dataFim = null;
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
		
	}
	
	@Test
	public void adicionarUmaSessaoComFilmeNulo() {

		Filme filme1 = null;
		Sala sala1 = new Sala(1); 
		LocalTime horario = LocalTime.of(20, 00);
		LocalDate dataInicio = LocalDate.of(2018, 05, 15);
		LocalDate dataFim = LocalDate.of(2018, 05, 30);
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
		
	}
	
	@Test
	public void adicionarUmaSessaoComSalaNula() {

		Filme filme1 = new Filme(1); //interestelar
		Sala sala1 = null; 
		LocalTime horario = LocalTime.parse("20:30");
		LocalDate dataInicio = LocalDate.parse("2018-05-22");
		LocalDate dataFim = LocalDate.parse("2018-05-30");
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
		
	}
	
	@Test
	public void excluirSessao() {
		filmeControlMock = Mockito.mock(FilmeController.class);
		Filme filme1 = new Filme(1); //interestelar
		
		Mockito.when(filmeControlMock.buscarFilmeId(1)).thenReturn(filme1);
		Mockito.when(filmeControlMock.buscarFilmeNome("interestelar")).thenReturn(filme1);
		
		salaControlMock= Mockito.mock(SalaController.class);
		
		Sala sala1 = new Sala(1); 
			
		Mockito.when(salaControlMock.buscarSalaId(1)).thenReturn(sala1);
		
		LocalTime horario = LocalTime.parse("20:30");
		LocalDate dataInicio = LocalDate.parse("2018-05-22");
		LocalDate dataFim = LocalDate.parse("2018-05-30");
		
		sessaoController.addSessao(filme1, sala1, horario, dataInicio, dataFim);
		sessaoController.excluirSessao(1);
		
		assertEquals(sessaoRepositorio.getOne(1), null);
	}
	
	@Test
	public void excluirSessaoComIdNaoSalvoNoBanco() {
		
		sessaoController.excluirSessao(1);
		//tenta excluir uma sessão de um banco vazio, ou seja não esta no banco
		
		assertEquals(sessaoRepositorio.getOne(1), null);
	}
	
}
