package com.app.dao;

import com.app.entity.DashboardLokasiEntity;
import com.app.entity.DashboardPaketEntity;
import com.app.entity.DashboardPpkEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Agung Andika on 01/09/2016.
 */
@Component
public class DashboardViewDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get List Dashboard PPK By ID Satker
     * @param idSatker String
     * @return List
     */
    public List<DashboardPpkEntity> getListByIdSatker(String idSatker) {
        String hql = "select u from DashboardPpkEntity u WHERE u.idSatker = :idSatker ORDER BY u.idPpk ASC";
        Query queryList = entityManager.createQuery(hql).setParameter("idSatker", idSatker);

        return queryList.getResultList();
    }

    /**
     * Get List Dashboard Paket By ID User
     * @param idUser String
     * @return List
     */
    public List<DashboardPaketEntity> getListByIdUserPaket(String idUser) {
        String hql = "SELECT u FROM DashboardPaketEntity u WHERE u.idUser = :idUser ORDER BY u.namaPaket ASC";
        Query queryList = entityManager.createQuery(hql).setParameter("idUser", idUser);

        return queryList.getResultList();
    }

    /**
     * Get List Dashboard Lokasi by ID Lokasi
     * @param idPaket String
     * @return List
     */
    public List<DashboardLokasiEntity> getListByIdPaket(String idPaket) {
        String hql = "SELECT u FROM DashboardLokasiEntity u WHERE u.paket = :idLokasi ORDER BY u.jenisPekerjaan ASC";
        Query queryList = entityManager.createQuery(hql).setParameter("idLokasi", idPaket);

        return queryList.getResultList();
    }
}
