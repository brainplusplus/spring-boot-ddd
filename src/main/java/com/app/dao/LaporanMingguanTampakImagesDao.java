package com.app.dao;

import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanTampakImages;
import com.app.entity.Prov;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author GHOZAL
 *
 */
public interface LaporanMingguanTampakImagesDao extends GenericDAORepository<LaporanMingguanTampakImages, String> {
	@Override
	@Query("select u from LaporanMingguanTampakImages u where u.keterangan like :search ORDER BY u.id desc")
	Page<LaporanMingguanTampakImages> searchPageByAllColumnLike(@Param("search") String search, Pageable page);

	@Query("select u from LaporanMingguanTampakImages u where u.idLaporan.id = :idLaporan ORDER BY u.id desc")
	Page<LaporanMingguanTampakImages> searchPageByLaporan(@Param("idLaporan") String idLaporan, Pageable page);

	@Query("select u from LaporanMingguanTampakImages u where u.idLaporan.id = :idLaporan and u.keterangan like :search ORDER BY u.id desc")
	Page<LaporanMingguanTampakImages> searchPageByLaporanNKet(@Param("idLaporan") String idLaporan,
			@Param("search") String search, Pageable page);

}
