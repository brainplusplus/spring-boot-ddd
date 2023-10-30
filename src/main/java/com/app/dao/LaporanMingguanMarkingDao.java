package com.app.dao;

import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanMarking;
import com.app.entity.Prov;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author GHOZAL
 *
 */
public interface LaporanMingguanMarkingDao extends GenericDAORepository<LaporanMingguanMarking, String> {
	@Override
	@Query("select u from LaporanMingguanMarking u where u.keterangan like :search ORDER BY u.id desc")
	Page<LaporanMingguanMarking> searchPageByAllColumnLike(@Param("search") String search, Pageable page);

	@Query("select u from LaporanMingguanMarking u where u.idLaporan.id = :idLaporan ORDER BY u.id desc")
	Page<LaporanMingguanMarking> searchPageByLaporan(@Param("idLaporan") String idLaporan, Pageable page);

	@Query("select u from LaporanMingguanMarking u where u.idLaporan.id = :idLaporan and u.keterangan like :search ORDER BY u.id desc")
	Page<LaporanMingguanMarking> searchPageByLaporanNKet(@Param("idLaporan") String idLaporan,
			@Param("search") String search, Pageable page);
        
        @Query("select u from LaporanMingguanMarking u where u.idLaporan.id = :idLaporan")
        List<LaporanMingguanMarking> findByIdLaporan(@Param("idLaporan") String idLaporan);
        
}
