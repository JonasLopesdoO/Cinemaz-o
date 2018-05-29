package br.ufc.vev.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "sessao")
public class Sessao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	@NotNull
	private LocalDate dataFim;
	@NotNull
	private LocalTime horario;
	
	@OneToOne
	private Filme filme;
	
	@OneToOne
	private Sala sala;
	
	public Sessao(Filme filme, Sala sala, LocalTime horario, LocalDate inicio, LocalDate fim) {
		this.setFilme(filme);
		this.setSala(sala);
		this.setHorario(horario);
		this.setDataInicio(inicio);
		this.setDataFim(fim);
	}
	
	public Sessao() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalTime getHorario() {
		return horario;
	}
	
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Sessao sessao = (Sessao) obj;
		if (this.getId() == sessao.getId()) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return "Sessao [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", horario=" + horario
				+ ", filme=" + filme + ", sala=" + sala + "]";
	}

	
}
