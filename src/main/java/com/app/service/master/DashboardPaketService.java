package com.app.service.master;

import com.app.dao.DashboardViewDao;
import com.app.entity.DashboardPaketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Only serving View, no save & update
 */

@Service
public class DashboardPaketService  {

    @Autowired
    private DashboardViewDao dashboardViewDao;

    public List<DashboardPaketEntity> getListByIdUserPaket(String idUser)
    {
        return dashboardViewDao.getListByIdUserPaket(idUser);
    }
}