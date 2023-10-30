package com.app.dao;

import com.app.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface UserDao extends GenericDAORepository<UserApp, String>{
	public UserApp findByUsername(String username);
	public Page<UserApp> findByUsernameLike(String username, Pageable page);
	
	@Override
	@Query("select u from UserApp u where u.username like :search")
	public Page<UserApp> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
	
	@Query("select u from UserApp u where u.username like :search and u.role.id = :roleId")
	public Page<UserApp> searchPageByAllColumnLikeAndRoleId(@Param("search") String search,@Param("roleId") String roleId, Pageable page);
	
	@Query("select u from UserApp u where u.role.id = :roleId")
	public Page<UserApp> searchPageByRoleId(@Param("roleId") String roleId, Pageable page);
        
        public UserApp findById(String id);
        
        @Query("select u from UserApp u where u.role.id = 2")
        public Iterable<UserApp> findByIdRolePengawas();
        
        @Query("select u from UserApp u where u.role.id = 3")
        public Iterable<UserApp> findByIdRoleRekanan();
}