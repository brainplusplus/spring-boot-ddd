/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.service.master;

import com.app.dao.MasterSatkerDAO;
import com.app.dao.RelSatkerPpkDao;
import com.app.dao.UserDao;
import com.app.entity.MasterSatker;
import com.app.entity.RelSatkerPpk;
import com.app.entity.UserApp;
import com.app.service.GenericDAOService;
import com.app.util.CommonUtil;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@Service
public class RelSatkerPpkService extends GenericDAOService<RelSatkerPpkDao, RelSatkerPpk>{
    
    @Autowired
    private RelSatkerPpkDao rspd;
    @Autowired
    private MasterSatkerDAO msd;
    @Autowired
    private UserDao ud;
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public RelSatkerPpk save(RelSatkerPpk k, String satker, String user) throws ParseException {
        k.setId(UUID.randomUUID().toString());

        if (!CommonUtil.isEmpty(satker)) {
            MasterSatker ms = msd.findById(satker);
            k.setIdSatker(ms);
        }
        if (!CommonUtil.isEmpty(user)) {
            UserApp usr = ud.findById(user);
            k.setIdUser(usr);
        }
        rspd.save(k);
        return k;
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public RelSatkerPpk update(RelSatkerPpk k, String satker, String user) throws ParseException, IllegalAccessException, InvocationTargetException {
        RelSatkerPpk entity = rspd.findOne(k.getId());
        BeanUtils.populate(entity, new BeanMap(k));
        
        if (!CommonUtil.isEmpty(satker)) {
            MasterSatker ms = msd.findById(satker);
            entity.setIdSatker(ms);
        }
        if (!CommonUtil.isEmpty(user)) {
            UserApp usr = ud.findById(user);
            entity.setIdUser(usr);
        }
        
        rspd.save(entity);
        return entity;
    }
}
