/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.api;

import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanMarking;
import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import com.app.entity.RegApp;
import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import com.app.object.api.request.laporanmingguan.ApiLaporanMingguanGetListRequest;
import com.app.object.api.request.laporanmingguan.ApiLaporanMingguanImagesGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageSaveRequest;
import com.app.object.api.request.user.ApiUserLoginRequest;
import com.app.object.api.response.user.ApiUserLoginResponse;
import com.app.service.master.GambarLokasiService;
import com.app.service.master.LaporanMingguanImagesService;
import com.app.service.master.LaporanMingguanMarkingService;
import com.app.service.master.LaporanMingguanService;
import com.app.service.master.LokasiService;
import com.app.service.master.RegAppService;
import com.app.service.master.SessionAppService;
import com.app.service.master.UserService;
import com.app.util.RandomGenerator;
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
public class ApiLaporanMingguanService {
    
    @Autowired
    private LaporanMingguanImagesService gls;
    
    @Autowired
    private LaporanMingguanMarkingService glms;
    
    @Autowired
    private LaporanMingguanService ls;
    
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
    
    public Page<LaporanMingguan> getListByIdLokasi(ApiLaporanMingguanGetListRequest param){
        Page<LaporanMingguan> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = ls.searchInt(param.rows, param.page, 0, param.idLokasi);
        }
        return resp;
    }
    
    public Page<LaporanMingguanImages> getImageListByIdLokasi(ApiLaporanMingguanImagesGetListRequest param){
        Page<LaporanMingguanImages> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = gls.SearchByLokasi(param.rows, param.page, "", param.idLaporan);
        }
        return resp;
    }
    
    public Page<LaporanMingguanMarking> getImageMarkingListByIdLokasi(ApiLaporanMingguanImagesGetListRequest param){
        Page<LaporanMingguanMarking> resp = null;
        RegApp regApp = null;
        if(param.appId!=null){
            regApp = ras.getById(param.appId);
        }
        SessionApp session = null;
        if(param.token!=null){
            session = sas.getSessionById(param.token);
        }
        if(session != null && regApp != null){
            resp = glms.SearchByLokasi(param.rows, param.page, "", param.idLaporan);
        }
        return resp;
    }
}
