package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

	/*
		Implementar un servicio Rest para la tabla concurso de la base de datos SQL_red_social
		- Lista
		- Inserta
		- Actualiza
		- Elimina
	*/
	
	@Query("select m from Medicamento m where m.nombre = ?1")
	public List<Medicamento> listaPorNombre(String nombre);
	
	@Query("select m from Medicamento m where m.idMedicamento = ?1 and m.nombre <> ?2")
	public List<Medicamento> listaPorIdMedicamentoDiferenteSiMismo(int idMedicamento, String nombre);
		
}
