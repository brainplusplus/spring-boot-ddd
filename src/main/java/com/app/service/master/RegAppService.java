/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.master;

import com.app.dao.RegAppDao;
import com.app.dao.RoleDao;
import com.app.entity.RegApp;
import com.app.entity.Role;
import com.app.service.GenericDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brainplusplus
 */
@Service
public class RegAppService extends GenericDAOService<RegAppDao, RegApp> {

		@Autowired
		private RegAppDao ud;
		
		public RegApp getById(String id){
			return ud.findById(id);
		}
}