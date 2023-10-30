package com.app.dao;

import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface SessionAppDao extends GenericDAORepository<SessionApp, String>{
	
        public SessionApp findById(String id);
        
        public SessionApp findBySessionDeviceAndVerificationCode(String sessionId,String verificationCode);

        public SessionApp findBySessionDevice(String sessionId);
        
        public SessionApp findBySessionDeviceAndDevice(String sessionId,String device);
   
        @Modifying
        @Transactional
	@Query("delete from SessionApp u where u.sessionDevice = :sessionDevice")
	public void removeBySessionDevice(@Param("sessionDevice") String sessionDevice);	
        
        @Modifying
        @Transactional
	@Query("delete from SessionApp u where u.sessionDevice = :sessionDevice and u.device = :device")
	public void removeBySessionDeviceAndDevice(@Param("sessionDevice") String sessionDevice,@Param("device") String device);
        
        @Override
	@Query("select u from SessionApp u where u.username like :search")
	public Page<SessionApp> searchPageByAllColumnLike(@Param("search") String search, Pageable page);
	

}