package com.app.service.master;

import com.app.dao.DashboardViewDao;
import com.app.entity.DashboardLokasiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Only serving View, no save & update
 */

@Service
public class DashboardLokasiService {

    @Autowired
    private DashboardViewDao dashboardViewDao;

    public List<DashboardLokasiEntity> getListByIdPaket(String idPaket)
    {
        return dashboardViewDao.getListByIdPaket(idPaket);
    }
}