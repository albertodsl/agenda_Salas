package com.agendasala.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="contato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idContato;
	@Column(nullable=false, length=50)
	private String nomeContato;
	@Column(nullable=false, length=70)
	private String email;
	@Column(nullable=false, length=15)
	private String numero;
	
	@OneToOne(mappedBy="contato", cascade=CascadeType.REMOVE)
	private Agendamento agendamento;
	
	public Contato() {
	}
	
	public Contato(Integer idContato, String nomeContato, String email, String numero) {
		this.idContato = idContato;
		this.nomeContato = nomeContato;
		this.email = email;
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return "Pessoa [idContato=" + idContato + ", nomeContato=" + nomeContato + ", email=" + email + ", numero="
				+ numero + "]";
	}

	public Integer getIdContato() {
		return idContato;
	}
	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	

}
