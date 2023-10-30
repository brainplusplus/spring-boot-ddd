/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.dao;

import com.app.entity.MasterSatker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
public interface MasterSatkerDAO extends GenericDAORepository<MasterSatker, String>{
    @Override
    @Query("select u from MasterSatker u where u.id like :search or u.kodeSatker like :search or u.namaSatker like :search or u.kepalaSatker like :search ORDER BY u.id desc")
    Page<MasterSatker> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
    
    public MasterSatker findById(String id);
}
