/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.dao;

import com.app.entity.Wilayah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
public interface WilayahDao extends GenericDAORepository<Wilayah, String>{
    
    @Override
    @Query("select u from Wilayah u where u.id like :search or u.namaWilayah like :search or u.keterangan like :search ORDER BY u.id desc")
    Page<Wilayah> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
    
    public Wilayah findById(String id);

}
