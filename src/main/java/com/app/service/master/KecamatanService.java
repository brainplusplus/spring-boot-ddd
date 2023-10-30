package com.app.service.master;

import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.KecamatanDao;
import com.app.entity.Kec;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.GenericDAOService;
import java.util.List;

@Service
public class KecamatanService extends GenericDAOService<KecamatanDao, Kec>{
	@Autowired
	private KecamatanDao vd;
        
        public List<Kec> searchListByIdKab(String kodeKab){
		return vd.searchListByIdKab(kodeKab);
	}

}
