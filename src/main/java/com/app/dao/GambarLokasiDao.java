package com.app.dao;

import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GambarLokasiDao extends GenericDAORepository<LokasiImages, String> {

    @Override
    @Query("select u from LokasiImages u where u.id like :search or u.namaFile like :search or u.keterangan like :search ORDER BY u.id desc")
    Page<LokasiImages> searchPageByAllColumnLike(@Param("search") String search, Pageable page);

    @Query("select u from LokasiImages u where u.type like :search OR u.idLokasi.id like :lokasi_id ORDER BY u.id desc")
    Page<LokasiImages> searchPageByAllColumnLike(@Param("search") Integer search, @Param("lokasi_id") String lokasiId, Pageable page);

    @Query("select u from LokasiImages u where u.idLokasi.id = :lokasi_id ORDER BY u.type desc")
    Page<LokasiImages> searchPageByLokasiId(@Param("lokasi_id") String lokasiId, Pageable page);

    @Query("select u from LokasiImages u where u.idLokasi.id = :idLokasi")
    List<LokasiImages> getAllByIdLokasi(@Param("idLokasi") String idLokasi);
    
}
