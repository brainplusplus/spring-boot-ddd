package com.app.service.master;

import com.app.dao.DashboardViewDao;
import com.app.entity.DashboardPpkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Only serving View, no save & update
 */

@Service
public class DashboardSatkerService {

    @Autowired
    private DashboardViewDao dashboardViewDao;

    public List<DashboardPpkEntity> getListByIdSatker(String idSatker) {
        return dashboardViewDao.getListByIdSatker(idSatker);
    }
}