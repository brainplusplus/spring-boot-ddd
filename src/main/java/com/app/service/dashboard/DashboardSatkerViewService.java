/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.dashboard;

import com.app.dao.DashboarSatkerDao;
import com.app.entity.DashboardSatkerEntity;
import com.app.object.dashboard.DashboardSatkerObject;
import com.app.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class DashboardSatkerViewService {
    
    @Autowired
    DashboarSatkerDao dashboardSatkerDao;
    
    public DashboardSatkerObject getData(){
        
        DashboardSatkerObject data = new DashboardSatkerObject();
        data.listDashboardSatker = dashboardSatkerDao.findAll();
        data.avgDeviasi = 0;
        data.avgRealisasi = 0;
        data.avgRencana = 0;
        data.avgDeviasiBackColor = "";
        data.jumlahSatuan = 0;
        data.jumlahCepat = 0;
        data.jumlahLambat = 0;
        data.jumlahLokasi = 0;
        
        for(DashboardSatkerEntity d:data.listDashboardSatker){
            data.avgDeviasi += d.getRealisasi()-d.getRencana();
            data.avgRealisasi += d.getRealisasi();
            data.avgRencana += d.getRencana();
            data.jumlahCepat += d.getCepat();
            data.jumlahLambat += d.getLambat();
            data.jumlahLokasi += d.getLokasi();
            data.jumlahSatuan += d.getJumlahSatuan();
        }
        
        data.avgDeviasi =  data.listDashboardSatker.size()>0?data.avgDeviasi/data.listDashboardSatker.size():0; 
        data.avgDeviasi = CommonUtil.round(data.avgDeviasi, 2);
        
        data.avgRealisasi = data.listDashboardSatker.size()>0?data.avgRealisasi/data.listDashboardSatker.size():0; 
        data.avgRealisasi = CommonUtil.round(data.avgRealisasi, 2);
        
        data.avgRencana = data.listDashboardSatker.size()>0?data.avgRencana/data.listDashboardSatker.size():0; 
        data.avgRencana = CommonUtil.round(data.avgRencana, 2);
            
        if (data.avgDeviasi < 0 && data.avgDeviasi >= -10) {
            data.avgDeviasiBackColor = "bg-yellow-crusta bg-font-yellow-crusta";
        } else if (data.avgDeviasi < -10) {
             data.avgDeviasiBackColor = "bg-red-thunderbird bg-font-red-thunderbird";
        } else if (data.avgDeviasi > 0) {
             data.avgDeviasiBackColor = "bg-blue-soft bg-font-blue-soft";
        } else {
             data.avgDeviasiBackColor = "";
        }
        
        return data;
    }
}
