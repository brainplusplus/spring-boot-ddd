package com.app.dao;

import com.app.entity.LaporanMingguan;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author GHOZAL
 *
 */
public interface LaporanMingguanDao extends  GenericDAORepository<LaporanMingguan, String> {
	@Override
	@Query("select u from LaporanMingguan u where u.laporanPeriodeMingguKe like :search or u.waktuPelaksanaan like :search or u.sisaWaktuPelaksanaan like :search ORDER BY u.laporanPeriodeMingguKe desc")
	Page<LaporanMingguan> searchPageByAllColumnLike(@Param("search") String search,Pageable page);

	@Query("select u from LaporanMingguan u where u.idLokasi.id = :lokasi_id and ( u.laporanPeriodeMingguKe = :search or u.rencanaProgressMingguIni = :search or u.progressMingguIni = :search or u.totalSerapan = :search ) ORDER BY u.laporanPeriodeMingguKe desc")
	Page<LaporanMingguan> searchPageByAllColumnLike(@Param("search") Integer search, @Param("lokasi_id") String lokasiId, Pageable page);

	@Query("select u from LaporanMingguan u where u.idLokasi.id = :lokasi_id ORDER BY u.laporanPeriodeMingguKe desc")
	Page<LaporanMingguan> searchPageByLokasiId(@Param("lokasi_id") String lokasiId, Pageable page);

	
		//PagingAndSortingRepository<PuKab, String> {
	public Page<LaporanMingguan> findByLaporanPeriodeMingguKeLike(String laporanPeriodeMingguKe, Pageable page);
	public Page<LaporanMingguan> findByWaktuPelaksanaanLike(String waktuPelaksanaan, Pageable page);
	public Page<LaporanMingguan> findBySisaWaktuPelaksanaanLike(String sisaWaktuPelaksanaan, Pageable page);
	public Page<LaporanMingguan> findByRencanaProgressMingguIniLike(String rencanaProgressMingguIni, Pageable page);
	public Page<LaporanMingguan> findByProgressMingguIniLike(String progressMingguIni, Pageable page);
	public Page<LaporanMingguan> findByTotalSerapanLike(String totalSerapan, Pageable page);
	
	@Query("from LaporanMingguan u LEFT JOIN FETCH u.idLokasi p WHERE u.id=:id")
	public LaporanMingguan findById(@Param("id") String id);
        
        @Query("select u.laporanPeriodeMingguKe,u.rencanaProgressMingguIni,u.progressMingguIni from LaporanMingguan u where u.idLokasi.id = :lokasiId and laporanPeriodeMingguKe <= :laporanKe ORDER BY u.laporanPeriodeMingguKe asc")
        List<LaporanMingguan> findByIdLokasiNLaporanKe(@Param("lokasiId") String lokasiId, @Param("laporanKe") Integer laporanKe);
        
        @Query("select u from LaporanMingguan u WHERE u.idLokasi.id=:id order by u.id desc")
	public List<LaporanMingguan> findByIdLokasiLeftJoin(@Param("id") String id);
}
