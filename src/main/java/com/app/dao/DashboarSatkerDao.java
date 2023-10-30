package com.app.dao;

import com.app.entity.DashboardSatkerEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DashboarSatkerDao extends GenericDAORepository<DashboardSatkerEntity, String> {
    @Override
    @Query("select u from DashboardSatkerEntity u where u.namaSatker = :search")
    public Page<DashboardSatkerEntity> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
    
    public DashboardSatkerEntity findById(String id);

    @Query("select SUM(u.jumlahSatuan) from DashboardSatkerEntity u")
    public int sumJumlahSatuan();

    @Query("select SUM(u.lokasi) from DashboardSatkerEntity u")
    public int sumJumlahLokasi();

    @Query("select ROUND(AVG(u.rencana), 2) from DashboardSatkerEntity u")
    public float avgRencana();

    @Query("select ROUND(AVG(u.realisasi), 2) from DashboardSatkerEntity u")
    public float avgRealisasi();

    @Query("select SUM(u.cepat) from DashboardSatkerEntity u")
    public int sumCepat();

    @Query("select SUM(u.lambat) from DashboardSatkerEntity u")
    public int sumLambat();

    @Query("select ROUND(AVG(u.realisasi - u.rencana), 2) from DashboardSatkerEntity u")
    public float avgDeviasi();

    @Override
    @Query("select u from DashboardSatkerEntity u")
    public List<DashboardSatkerEntity> findAll();
}