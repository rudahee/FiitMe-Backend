package com.fiitme.service.dtoConverters;

import java.util.ArrayList;
import java.util.List;

public abstract class DtoConverter<E, D> {

	public abstract E fromDto(D dto);
	
	public abstract D fromEntity(E entity);
	
	public List<D> fromEntities(List<E> entities) {
		List<D> dtos = new ArrayList<D>();
		entities.forEach(entity -> {
			dtos.add(this.fromEntity(entity));
		});
		
		return dtos;
	}
	
	public List<E> fromDtos(List<D> dtos) {
		List<E> entities = new ArrayList<E>();
		dtos.forEach(dto -> {
				entities.add(this.fromDto(dto));
		});
		
		return entities;
	}
}
