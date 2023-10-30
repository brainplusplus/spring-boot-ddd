/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.api;

import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import com.app.entity.RegApp;
import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import com.app.object.api.request.lokasi.ApiLokasiGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageSaveRequest;
import com.app.object.api.request.user.ApiUserLoginRequest;
import com.app.object.api.response.user.ApiUserLoginResponse;
import com.app.service.master.GambarLokasiService;
import com.app.service.master.LokasiService;
import com.app.service.master.RegAppService;
import com.app.service.master.SessionAppService;
import com.app.service.master.UserService;
import com.app.util.RandomGenerator;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brainplusplus
 */
@Service
public class ApiLokasiService {
    
    @Autowired
    private GambarLokasiService gls;
    
    @Autowired
    private LokasiService ls;
    
    @Autowired
    private UserService us;
    
    @Autowired
    private SessionAppService sas;

    @Autowired
    private RegAppService ras;
    
    @PersistenceContext
    private EntityManager em;
    
    public long count(){
        return ls.count();
    }
    
    public Page<LokasiSurvey> getList(ApiLokasiGetListRequest param){
        Page<LokasiSurvey> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = ls.findAll(param.page,param.rows);
        }
        return resp;
    }
    
    public Page<LokasiSurvey> getListByIdUser(ApiLokasiGetListRequest param){
        Page<LokasiSurvey> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = ls.findAllByIdUser(param.idUser,param.page,param.rows);
        }
        return resp;
    }
    
    
    public Page<LokasiImages> getImageListByIdLokasi(ApiLokasiImageGetListRequest param){
        Page<LokasiImages> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = gls.searchInt(param.rows, param.page, 0, param.idLokasi);
        }
        return resp;
    }
    
    public LokasiImages save(ApiLokasiImageSaveRequest param){
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            return gls.save(param.lokasiImage, param.type, param.idLokasi, param.image);
        }
        return param.lokasiImage;
    }
    
    public LokasiImages update(ApiLokasiImageSaveRequest param) throws IllegalAccessException, InvocationTargetException{
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            return gls.update(param.lokasiImage, param.type, param.idLokasi, param.image);
        }
        return param.lokasiImage;
    }
}
