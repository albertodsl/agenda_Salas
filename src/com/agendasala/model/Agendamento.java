package com.agendasala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@XmlRootElement
@Entity
@Table(name="agendamento")
public class Agendamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idAgendamento;
	@Column(nullable=false, length=10)
	private String publico;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date fim;
	@Column(length=100)
	private String pauta;
	@Column
	private Integer participantes;
	@Column
	private Integer vagas;
	@Column(length=255)
	private String recursos;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_contato")
	private Contato contato;
	
	@ManyToOne
	@JsonBackReference
	private Sala sala;

	public Agendamento() {
	}

	public Agendamento(Integer idAgendamento, String publico, Date inicio, Date fim, String pauta,
			Integer participantes, Integer vagas, String recursos) {
		this.idAgendamento = idAgendamento;
		this.publico = publico;
		this.inicio = inicio;
		this.fim = fim;
		this.pauta = pauta;
		this.participantes = participantes;
		this.vagas = vagas;
		this.recursos = recursos;
	}

	@Override
	public String toString() {
		return "Agendamento [idAgendamento=" + idAgendamento + ", publico=" + publico + ", inicio=" + inicio + ", fim="
				+ fim + ", pauta=" + pauta + ", participantes=" + participantes + ", vagas=" + vagas + ", recursos="
				+ recursos + ", contato=" + contato + "]";
	}

	public Integer getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Integer idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public String getPublico() {
		return publico;
	}

	public void setPublico(String publico) {
		this.publico = publico;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public Integer getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Integer participantes) {
		this.participantes = participantes;
	}

	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public String getRecursos() {
		return recursos;
	}

	public void setRecursos(String recursos) {
		this.recursos = recursos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	

}
