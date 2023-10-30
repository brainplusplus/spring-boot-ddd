/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.service.master;

import com.app.dao.MasterSatkerDAO;
import com.app.entity.MasterSatker;
import com.app.service.GenericDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */

@Service
public class MasterSatkerService extends GenericDAOService<MasterSatkerDAO, MasterSatker>{
    
    @Autowired
    private MasterSatkerDAO msd;

}
