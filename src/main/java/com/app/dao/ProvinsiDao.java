package com.app.dao;

import com.app.entity.Prov;
import java.util.Iterator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProvinsiDao extends GenericDAORepository<Prov, String> {
	@Override
	@Query("select u from Prov u where u.kode like :search or u.nama like :search ORDER BY u.id desc")
	Page<Prov> searchPageByAllColumnLike(@Param("search") String search,Pageable page);

		//PagingAndSortingRepository<PuProv, String> {
	public Page<Prov> findByNamaLike(String nama, Pageable page);
	public Page<Prov> findByKodeLike(String kode, Pageable page);
        
        @Query("select u from Prov u where u.kode = :kodeProv")
	public Prov findByKode(@Param("kodeProv") String kodeProv);
        
        @Override
        @Query("select u from Prov u")
        public Iterable<Prov> findAll();
}
