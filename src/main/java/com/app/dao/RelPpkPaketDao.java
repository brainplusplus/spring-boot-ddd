/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.dao;

import com.app.entity.RelPpkPaket;
import com.app.entity.UserApp;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
public interface RelPpkPaketDao extends GenericDAORepository<RelPpkPaket, String>{
    @Override
    @Query("select u from RelPpkPaket u where u.id like :search or u.idPaket like :search or u.idUser like :search ORDER BY u.id desc")
    Page<RelPpkPaket> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
    
    @Query("select u from RelPpkPaket u where u.idUser = :id_user")
    List<RelPpkPaket> findByIdUser(@Param("id_user") UserApp id);

}
