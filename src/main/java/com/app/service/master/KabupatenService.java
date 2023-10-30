package com.app.service.master;

import com.app.dao.KabupatenDao;
import com.app.entity.Kab;
import com.app.service.GenericDAOService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hendra on 09/11/2015.
 */
@Service
public class KabupatenService extends GenericDAOService<KabupatenDao, Kab> {
	@Autowired
	private KabupatenDao vd;
        
        public List<Kab> searchListByIdProv(String kodeProv){
		return vd.searchListByIdProv(kodeProv);
	}
        
	/*public ResponseListObject search(int rows, int page, String search) {
		ResponseListObject response = new ResponseListObject();
		search = search == null ? "%%" : "%" + search + "%";
		try {
			PageRequest paginationRequest = new PageRequest(page, rows);
			Page<PuKab> hasil = vd.findByNamaLike(search, paginationRequest);
			response.recordsTotal = vd.count();
			response.recordsFiltered = hasil.getTotalElements();
			response.rows = hasil.getContent();
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();

		}
		return response;
	}
	public ResponseObject save(PuKab k) {
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
	public ResponseObject update(PuKab k) {
		ResponseObject response = new ResponseObject();
		try {
			PuKab vendor = vd.findOne(k.getId());
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
			PuKab hasil = vd.findOne(id);
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
