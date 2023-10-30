/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.service.master;

import com.app.dao.WilayahDao;
import com.app.entity.Wilayah;
import com.app.service.GenericDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@Service
public class WilayahService extends GenericDAOService<WilayahDao, Wilayah>{
    
    @Autowired
    private WilayahDao wd;

}
