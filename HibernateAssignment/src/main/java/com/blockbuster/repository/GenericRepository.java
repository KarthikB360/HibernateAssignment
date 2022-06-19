package com.blockbuster.repository;

import java.util.List;

public interface GenericRepository {

	public Object save(Object obj);

	public <E> E findById(Class<E> clazz, Object pk);

	public void saveBatch(List<Object> obj);
}
