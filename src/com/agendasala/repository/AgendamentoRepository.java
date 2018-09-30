package com.agendasala.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.agendasala.model.Agendamento;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Integer> {
	
	@Query("select a from Agendamento a where :inicio between a.inicio and a.fim or :fim between a.inicio and a.fim or "
			+ "(:inicio <= a.inicio and :fim >= a.fim)")
	public List<Agendamento> isAgendado(@Param("inicio")Date inicio, @Param("fim")Date fim);
	
	@Query("select a from Agendamento a where a.sala.idSala = :sala")
	public List<Agendamento> findBySala(@Param("sala")Integer sala);
	
	@Query("select a from Agendamento a where extract(year_month_day from a.inicio) = :data")
	public List<Agendamento> findByDate(@Param("data")Date data);
	
	
	
}