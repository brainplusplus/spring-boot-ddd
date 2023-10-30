package com.app.dao;

import com.app.entity.Kab;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KabupatenDao extends  GenericDAORepository<Kab, String> {
	@Override
	@Query("select u from Kab u where u.kodeKab like :search or u.namaKab like :search ORDER BY u.id desc")
	Page<Kab> searchPageByAllColumnLike(@Param("search") String search,Pageable page);

		//PagingAndSortingRepository<PuKab, String> {
	public Page<Kab> findByNamaKabLike(String nama, Pageable page);
	public Page<Kab> findByKodeKabLike(String kode, Pageable page);
        
        @Query("select u from Kab u where u.kodeKab = :kodeKab")
	public Kab findByKode(@Param("kodeKab") String kodeKab);
        
        @Query("select u from Kab u where u.kodeProv.kode = :kodeProv")
	List<Kab> searchListByIdProv(@Param("kodeProv") String kodeProv);
}
