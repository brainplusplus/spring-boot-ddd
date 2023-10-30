package com.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.app.entity.Role;

public interface RoleDao extends GenericDAORepository<Role, String>{
	public Role findByNama(String nama);
	
	
	@Override
	@Query("select u from Role u where u.nama like :search")
	public Page<Role> searchPageByAllColumnLike(@Param("search") String search, Pageable page);	
}