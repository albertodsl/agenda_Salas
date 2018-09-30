package com.agendasala.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@XmlRootElement
@Entity
@Table(name="sala")
public class Sala implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idSala;
	@Column(nullable=false, length=15)
	private String tipoSala;
	@Column(nullable=false)
	private Integer capacidade;
	@Column(nullable=false)
	private Integer andar;
	
	@OneToMany(mappedBy="sala", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JsonManagedReference
    private List<Agendamento> agenda;
	
	public Sala() {
	}

	public Sala(Integer idSala, String tipoSala, Integer capacidade, Integer andar) {
		this.idSala = idSala;
		this.tipoSala = tipoSala;
		this.capacidade = capacidade;
		this.andar = andar;
	}

	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", tipoSala=" + tipoSala + ", capacidade=" + capacidade + ", andar=" + andar
				+ "]";
	}

	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

	public String getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(String tipoSala) {
		this.tipoSala = tipoSala;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	

}
