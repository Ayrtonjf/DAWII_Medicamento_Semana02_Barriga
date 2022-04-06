package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {
	
	
	
	
	
	// - Listar TODOS
	public abstract List<Medicamento> listaMedicamento();
	
	// - Inserta / Actualiza
	public abstract Medicamento insertaActualizaMedicamento(Medicamento obj);
	
	// - Listar único (nombre)
	public abstract List<Medicamento> listaMedicamentosPorNombre(String nombre);
	
	// valida unico (id - nombre)
	public abstract List<Medicamento> listaMedicamentoPorIdDiferenteDelMismo(int idMedicamento, String nombre);
	
	// Listar - Optional (id)
	public abstract Optional<Medicamento> listaMedicamentoPorId(int idMedicamento);
	
	// - Elimina
	public abstract void eliminaPorId(int idMedicamento);
	
}
