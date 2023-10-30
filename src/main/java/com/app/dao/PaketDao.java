package com.app.dao;

import com.app.entity.Paket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PaketDao extends GenericDAORepository<Paket, String> {
    @Override
    @Query("select u from Paket u where u.namaPaket like %:search% OR u.kodePaket like %:search%")
    public Page<Paket> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
    
    @Query("select u from Paket u where u.tahun like %:tahun% AND (u.namaPaket like %:search% OR u.kodePaket like %:search%)")
    public Page<Paket> searchPageByTahunAndAllColumnLike(@Param("search") String search, @Param("tahun") String tahun, Pageable page);
    
    public Paket findById(String id);
}