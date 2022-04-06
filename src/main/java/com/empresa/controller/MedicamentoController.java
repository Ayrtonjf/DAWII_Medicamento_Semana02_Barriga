package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("rest/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamento(){
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaAlumno(@RequestBody Medicamento obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Medicamento> lstMedicamento = service.listaMedicamentosPorNombre(obj.getNombre());
			if(CollectionUtils.isEmpty(lstMedicamento)) {
				obj.setIdMedicamento(0);
				Medicamento objSalida = service.insertaActualizaMedicamento(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro");
				} else {
					salida.put("mensaje", "Registro exitoso");
				}
			} else {
				salida.put("mensaje", "El medicamento ya existe : " + obj.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizarMedicamento(@RequestBody Medicamento obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Medicamento> optional = service.listaMedicamentoPorId(obj.getIdMedicamento());
			if(optional.isPresent()) {
				List<Medicamento> lstMedicamento = service.listaMedicamentoPorIdDiferenteDelMismo(obj.getIdMedicamento(), obj.getNombre());
				if(CollectionUtils.isEmpty(lstMedicamento)) {
					Medicamento objSalida = service.insertaActualizaMedicamento(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en Actualizar");
					} else {
						salida.put("mensaje", "Actualización Exitosa");
					}
				} else {
					salida.put("mensaje", "El Medicamento ya existe : " + obj.getNombre());
				}
			} else {
				salida.put("mensaje", "El id del Medicamento ya existe : " + obj.getIdMedicamento());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualización " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminaMedicamento(@PathVariable int id) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Medicamento> optional = service.listaMedicamentoPorId(id);
			if (optional.isPresent()) {
				service.eliminaPorId(id);
				salida.put("mensaje", "Eliminación Exitosa");
			} else {
				salida.put("mensaje", "El ID no existe : " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/nombre/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentosPorNombre(@PathVariable String nombre) {
		List<Medicamento> lista = service.listaMedicamentosPorNombre(nombre);
		return ResponseEntity.ok(lista);
	}
	
}
