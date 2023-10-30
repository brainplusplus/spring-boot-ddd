package com.app.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericDAORepository<T,M extends Serializable> extends PagingAndSortingRepository<T, M>{
	public Page<T> searchPageByAllColumnLike(String search, Pageable page);
}
