/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.entity.RegApp;
import com.app.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author brainplusplus
 */
public interface RegAppDao extends GenericDAORepository<RegApp, String>{
	public RegApp findById(String id);
	
	
	@Override
	@Query("select u from RegApp u where u.name like :search")
	public Page<RegApp> searchPageByAllColumnLike(@Param("search") String search, Pageable page);	
}