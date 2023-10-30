package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.app.entity.RolePage;

public interface RolePageDao extends GenericDAORepository<RolePage, String>{
	@Override
	@Query("select u from RolePage u where u.idRole.nama like :search or u.idPage.nama like :search")
	public Page<RolePage> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
	
	@Query("select u from RolePage u where u.idRole.id = :role_id and (u.idRole.nama like :search or u.idPage.nama like :search)")
	public Page<RolePage> searchPageByAllColumnLikeAndRoleId(@Param("search") String search, @Param("role_id") String roleId, Pageable page);	
	
	@Query("select u from RolePage u where u.idRole.id = :role_id")
	public Page<RolePage> searchPageByRoleId(@Param("role_id") String roleId, Pageable page);	
	
	@Query("select u from RolePage u where u.idRole.id = :role_id")
	public List<RolePage> searchAllByRoleId(@Param("role_id") String roleId);	
	
}