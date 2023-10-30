package com.app.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.GenericDAORepository;
import com.app.entity.BaseModel;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;

public abstract class GenericDAOService<D extends GenericDAORepository,M extends BaseModel> {
	@Autowired
	private D vd;

	public Page<M> search(int rows, int page, String search) {
		search = search == null ? "%%" : "%" + search + "%";
		PageRequest paginationRequest = new PageRequest(page, rows);
		Page<M> hasil = vd.searchPageByAllColumnLike(search, paginationRequest);
		return hasil;
	}

	@Transactional(readOnly = false , rollbackFor = Exception.class)
	public M save(M k) throws ParseException {
		k.setId(UUID.randomUUID().toString());
		//k.setDateCreated(new Date());
		//k.setDateUpdate(new Date());
		vd.save(k);
		return k;
	}

	@Transactional(readOnly = false , rollbackFor = Exception.class)
	public M update(M k) throws IllegalAccessException, InvocationTargetException {
		DozerBeanMapper doz = new DozerBeanMapper();
		M entity = (M) vd.findOne(k.getId());
                //k.setDateCreated(entity.getDateCreated());
		doz.map(k, entity);
		//entity.setDateUpdate(new Date());
		vd.save(entity);
		return k;
	}

	public M findOne(String id) {
		M hasil = (M) vd.findOne(id);
		return hasil;
	}

	@Transactional(readOnly = false , rollbackFor = Exception.class)
	public void remove(String id) {
		vd.delete(id);
	}
	
	public Iterable<M> findAll() {
		return vd.findAll();
	}
	
	public long count() {
		return vd.count();
	}

}
