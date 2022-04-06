package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;

	@Override
	public List<Medicamento> listaMedicamento() {
		// De JpaRepository
		return repository.findAll();
	}

	@Override
	public Medicamento insertaActualizaMedicamento(Medicamento obj) {
		// De JpaRepository
		return repository.save(obj);
	}

	@Override
	public List<Medicamento> listaMedicamentosPorNombre(String nombre) {
		// @Query
		return repository.listaPorNombre(nombre);
	}

	@Override
	public List<Medicamento> listaMedicamentoPorIdDiferenteDelMismo(int idMedicamento, String nombre) {
		// @Query
		return repository.listaPorIdMedicamentoDiferenteSiMismo(idMedicamento, nombre);
	}

	@Override
	public Optional<Medicamento> listaMedicamentoPorId(int idMedicamento) {
		// De JpaRepository - Optional
		return repository.findById(idMedicamento);
	}

	@Override
	public void eliminaPorId(int idMedicamento) {
		// De JpaRepository		
		repository.deleteById(idMedicamento);
	}

	
	
}
