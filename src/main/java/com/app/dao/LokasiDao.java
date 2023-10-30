/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.dao;

import com.app.entity.LokasiSurvey;
import com.app.entity.UserApp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Dimas <ryan.hawari at gmail.com>
 */
public interface LokasiDao extends GenericDAORepository<LokasiSurvey, String> {
        @Override
	@Query("select u from LokasiSurvey u where u.namaLokasi like :search OR u.tahun like :search ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByAllColumnLike(@Param("search") String search,Pageable page);
        
        @Query("select u from LokasiSurvey u where u.tahun = :tahun ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByTahun(@Param("tahun") String tahun,Pageable page);

	@Query("select u from LokasiSurvey u where u.tahun like %:tahun% AND (u.namaLokasi like :search OR u.alamat like :search) ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByAllColumnAndTahunLike(@Param("search") String search,@Param("tahun") String tahun,Pageable page);
        
        @Query("select u from LokasiSurvey u where u.idUserPengawas=:user ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByUserPengawas(@Param("user") String user, Pageable page);
        
        @Query("select u from LokasiSurvey u where u.idUserRekanan=:user ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByUserRekanan(@Param("user") String user, Pageable page);
        
        @Query("select u from LokasiSurvey u where (u.namaLokasi LIKE :search OR u.alamat LIKE :search) AND u.tahun like %:tahun% AND u.idUserPengawas=:user ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByPengawasAndAllColumnAndTahunLike(@Param("user")String user, @Param("search") String search,@Param("tahun") String tahun,Pageable page);
        
        @Query("select u from LokasiSurvey u where (u.namaLokasi LIKE :search OR u.alamat LIKE :search) AND u.tahun like %:tahun% AND u.idUserRekanan=:user ORDER BY u.id desc")
	Page<LokasiSurvey> searchPageByRekananAndAllColumnAndTahunLike(@Param("user")String user, @Param("search") String search,@Param("tahun") String tahun,Pageable page);
        
	public Page<LokasiSurvey> findByNamaLokasiLike(String namaLokasi, Pageable page);

	public LokasiSurvey findById(String id);
        
        public Page<LokasiSurvey> findByIdUser(String idUser,Pageable page);
        public List<LokasiSurvey> findByIdUser(UserApp userApp);
        
	@Query("select u from LokasiSurvey u where u.idUser.id = :idUser")
	Page<LokasiSurvey> getAllByIdUser(@Param("idUser") String idUser,Pageable page);
        
        @Query("select u from LokasiSurvey u where u.idUser.id = :idUser")
	List<LokasiSurvey> getAllByIdUser(@Param("idUser") String idUser);
        
        @Query("select u from LokasiSurvey u")
	List<LokasiSurvey> getAll();
        
        @Query("select u from LokasiSurvey u")
	Page<LokasiSurvey> getAll(Pageable page);

        @Query("select u from LokasiSurvey u where u.kodeProv.kode=:kodeProv")
        public List<LokasiSurvey> findByKodeProv(@Param("kodeProv") String kodeProv);
        
        @Query("select u from LokasiSurvey u where u.kodeKab.kodeKab=:kodeKab")
        public List<LokasiSurvey> findByKodeKab(@Param("kodeKab") String kodeKab);
        
        @Query("select u from LokasiSurvey u where u.idPaket in (:paket) AND u.kodeProv.kode=:kodeProv")
        public List<LokasiSurvey> findByIdPaketAndKodeProv(@Param("paket") ArrayList paket,@Param("kodeProv") String kodeProv);
        
        @Query("select u from LokasiSurvey u where u.idPaket in (:paket) AND u.kodeKab.kodeKab=:kodeKab")
        public List<LokasiSurvey> findByIdPaketAndKodeKab(@Param("paket") ArrayList paket,@Param("kodeKab") String kodeKab);
}
