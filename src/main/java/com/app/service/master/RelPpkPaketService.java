/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.service.master;

import com.app.dao.PaketDao;
import com.app.dao.RelPpkPaketDao;
import com.app.dao.UserDao;
import com.app.entity.Paket;
import com.app.entity.RelPpkPaket;
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
public class RelPpkPaketService extends GenericDAOService<RelPpkPaketDao, RelPpkPaket>{
    
    @Autowired
    private RelPpkPaketDao rppd;
    
    @Autowired
    private UserDao ud;
    
    @Autowired
    private PaketDao pd;
    
     @Transactional(readOnly = false, rollbackFor = Exception.class)
    public RelPpkPaket save(RelPpkPaket k, String paket, String user) throws ParseException {
        k.setId(UUID.randomUUID().toString());

        if (!CommonUtil.isEmpty(paket)) {
            Paket p = pd.findById(paket);
            k.setIdPaket(p);
        }
        if (!CommonUtil.isEmpty(user)) {
            UserApp usr = ud.findById(user);
            k.setIdUser(usr);
        }
        rppd.save(k);
        return k;
    }
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public RelPpkPaket update(RelPpkPaket k, String paket, String user) throws ParseException, IllegalAccessException, InvocationTargetException {
        RelPpkPaket entity = rppd.findOne(k.getId());
        BeanUtils.populate(entity, new BeanMap(k));
        
        if (!CommonUtil.isEmpty(paket)) {
            Paket p = pd.findById(paket);
            entity.setIdPaket(p);
        }
        if (!CommonUtil.isEmpty(user)) {
            UserApp usr = ud.findById(user);
            entity.setIdUser(usr);
        }
        
        rppd.save(entity);
        return entity;
    }

}
