package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import java.util.List;

public interface GenericDao<T> {

	Long save(T c);

	T get(Long id);

	List<T> list();

	void update(T c);
	
	void delete(T c);
}