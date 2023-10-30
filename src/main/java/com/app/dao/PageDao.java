package com.app.dao;

import com.app.entity.PageApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PageDao extends GenericDAORepository<PageApp, String>{
	@Override
	@Query("select u from PageApp u where u.nama like :search")
	public Page<PageApp> searchPageByAllColumnLike(@Param("search") String search, Pageable page);	
}