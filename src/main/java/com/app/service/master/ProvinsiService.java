package com.app.service.master;

import com.app.dao.ProvinsiDao;
import com.app.entity.Prov;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.GenericDAOService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by hendra on 09/11/2015.
 */
@Service
public class ProvinsiService extends GenericDAOService<ProvinsiDao, Prov> {
	@Autowired
	private ProvinsiDao vd;

	/*public ResponseListObject search(int rows, int page, String search) {
		ResponseListObject response = new ResponseListObject();
		search = search == null ? "%%" : "%" + search + "%";
		try {
			PageRequest paginationRequest = new PageRequest(page, rows);
			Page<PuProv> hasil = vd.findByNamaLike(search, paginationRequest);
			response.recordsTotal = vd.count();
			response.recordsFiltered = hasil.getTotalElements();
			response.rows = hasil.getContent();
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();

		}
		return response;
	}
	public ResponseObject save(PuProv k) {
		ResponseObject response = new ResponseObject();
		try {
			k.setId(UUID.randomUUID().toString());
			vd.save(k);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
	public ResponseObject update(PuProv k) {
		ResponseObject response = new ResponseObject();
		try {
			PuProv vendor = vd.findOne(k.getId());
			BeanUtils.populate(vendor, new BeanMap(k));
			vd.save(vendor);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
	public ResponseObject findOne(String id) {
		ResponseObject response = new ResponseObject();
		try {
			PuProv hasil = vd.findOne(id);
			if (hasil == null) {
				throw new Exception("Data tidak ditemukan");
			}
			response.data = hasil;
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
	public ResponseObject remove(String id) {
		ResponseObject response = new ResponseObject();
		try {
			vd.delete(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}*/
}
