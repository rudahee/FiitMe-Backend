package com.fiitme.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fiitme.model.entity.AbstractEntity;

public abstract class AbstractController  <T extends AbstractEntity, D extends AbstractDTO, S extends BasePersistenceService<T, D, Long>> implements
IBaseController<T, Long> {

	@Autowired
	protected S service;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<T> entities = service.findAll();
		HttpStatus status = HttpStatus.OK;
		List<D> dtos = new ArrayList<D>();
		
		entities.forEach(entity -> {
			dtos.add(dtoConverter.fromEntity(entity) );
		});
		return ResponseEntity.status(status).body(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		T entity = service.find(id);
		HttpStatus status = HttpStatus.OK;
		return ResponseEntity.status(status).body(dtoConverter.fromEntity(entity));
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody T entity) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorCode.INDETERMINATE_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody T entity) {
		T newEntity = service.update(entity, id);
		HttpStatus status = HttpStatus.OK;
		return ResponseEntity.status(status).body(newEntity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		T entity = service.find(id);
		service.delete(id);
		HttpStatus status = HttpStatus.OK;
		return ResponseEntity.status(status).body(entity);
	}
	
}