package com.app.dao;

import com.app.entity.Kec;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KecamatanDao extends GenericDAORepository<Kec, String> {
	@Override
	@Query("select u from Kec u where u.namakec like :search or u.kodekec like :search")
	Page<Kec> searchPageByAllColumnLike(@Param("search") String search,Pageable page);
	
	public Page<Kec> findByNamakecLike(String nama, Pageable page);

	public Page<Kec> findByKodekecLike(String kode, Pageable page);
        
        @Query("select u from Kec u where u.kodekab = :kodekab")
	List<Kec> searchListByIdKab(@Param("kodekab") String kodekab);
}
