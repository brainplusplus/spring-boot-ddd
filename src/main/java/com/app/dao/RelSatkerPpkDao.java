/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.entity.RelSatkerPpk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author dimasrh <ryan.hawari at gmail.com>
 */
public interface RelSatkerPpkDao extends GenericDAORepository<RelSatkerPpk, String> {
    @Override
    @Query("select u from RelSatkerPpk u where u.id like :search or u.idSatker like :search or u.idUser like :search ORDER BY u.id desc")
    Page<RelSatkerPpk> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
}
