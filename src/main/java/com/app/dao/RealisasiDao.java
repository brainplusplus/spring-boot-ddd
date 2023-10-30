package com.app.dao;

import com.app.entity.SerapanRealisasiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RealisasiDao extends GenericDAORepository<SerapanRealisasiEntity, String> {

    @Query("SELECT sr FROM SerapanRealisasiEntity sr WHERE sr.jenisRealisasi LIKE :search ORDER BY sr.tanggalRealisasi DESC")
    public Page<SerapanRealisasiEntity> searchPageByAllColumnLike(@Param("search") String search, Pageable page);

    @Query("SELECT sr FROM SerapanRealisasiEntity sr WHERE sr.idLokasi.id = :lokasi_id AND (sr.jenisRealisasi LIKE %:search% OR sr.kopRealisasi LIKE %:search% OR sr.keterangan LIKE %:search%) ORDER BY sr.tanggalRealisasi DESC")
    public Page<SerapanRealisasiEntity> searchPageByAllColumnLike(@Param("search") String search, @Param("lokasi_id") String lokasiId, Pageable page);

    @Query("SELECT sr FROM SerapanRealisasiEntity sr WHERE sr.idLokasi.id = :lokasi_id ORDER BY sr.tanggalRealisasi DESC")
    public Page<SerapanRealisasiEntity> searchPageByLokasiId(@Param("lokasi_id") String lokasiId, Pageable page);
}