package com.fiitme.service.entityCruds;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.fiitme.model.entity.AbstractEntity;
import com.fiitme.service.dtoConverters.DtoConverter;

public class AbstractCrudService<E extends AbstractEntity, D, DC extends DtoConverter<E, D>, R extends CrudRepository<E, ID>, ID> {

	/*
	 * E -> Entity
	 * D -> DTO
	 * DC -> DTO Converter
	 * ID -> Id Type
	 * R -> Repository
	 */
	
	@Autowired
	protected R repository;
	
	@Autowired
	protected DC DtoConverter;
	
	
	public E save(E entity) {
		return repository.save(entity);
	}
	
	public D saveFromDto(D dto) {
		return DtoConverter.fromEntity(repository.save(DtoConverter.fromDto(dto)));
	}
	
	public Iterable<E> saveAll(List<E> entities) {
		return repository.saveAll(entities);
	}

	public Iterable<D> SaveAllFromDto(List<D> dtos) {
		return DtoConverter.fromEntities(repository.saveAll(DtoConverter.fromDtos(dtos)));
	}

	public Optional<E> findById(ID id) {
		return repository.findById(id);
	}
	
	public Iterable<E> findAll() {
		return repository.findAll();
	}
	
	public E edit(E entity) {
		return repository.save(entity);
	}
	
	public void delete(E entity) {
		repository.delete(entity);
	}
	
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}
	
}


