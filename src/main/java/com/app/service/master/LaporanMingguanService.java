package com.app.service.master;

import com.app.dao.LaporanMingguanDao;
import com.app.entity.LaporanMingguan;
import com.app.entity.LokasiSurvey;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.StringToDateConverter;
import com.app.service.GenericDAOService;
import com.app.util.CommonUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LaporanMingguanService extends GenericDAOService<LaporanMingguanDao, LaporanMingguan> {
	@Autowired
	private LaporanMingguanDao vd;
	
	@Autowired
	private LokasiService ls;
	
	public Page<LaporanMingguan> searchInt(int rows, int page, Integer search, String lokasiId) {
//		search = search == null ? "%%" :"%"+  search +"%";
		search = search == null ? 0 :  search ;
		PageRequest paginationRequest = new PageRequest(page, rows);
		Page<LaporanMingguan> hasil = null;
		if(search != 0 && !CommonUtil.isEmpty(lokasiId)){
			hasil = vd.searchPageByAllColumnLike(search,lokasiId, paginationRequest);
		}else if(search == 0 && !CommonUtil.isEmpty(lokasiId)){
			hasil = vd.searchPageByLokasiId(lokasiId, paginationRequest);
		}
		return hasil;
	}
	
	@Transactional(readOnly = false , rollbackFor = Exception.class)
	public LaporanMingguan save(LaporanMingguan k, String lokasiId) {
		k.setId(UUID.randomUUID().toString());
		//k.setDateCreated(new Date());
		//k.setDateUpdate(new Date());
		if(lokasiId !="" || lokasiId != null){
			LokasiSurvey lokasi = ls.findOne(lokasiId);
			if(lokasi != null){
				k.setIdLokasi(lokasi);
			}
		}
		
		vd.save(k);
		return k;
	}
	
	@Transactional(readOnly = false , rollbackFor = Exception.class)
	public LaporanMingguan update(LaporanMingguan k, String lokasiId) throws IllegalAccessException, InvocationTargetException {
		DozerBeanMapper doz = new DozerBeanMapper();
		LaporanMingguan entity = (LaporanMingguan) vd.findOne(k.getId());
                //k.setDateCreated(entity.getDateCreated());
		doz.map(k, entity);
		if(lokasiId !="" || lokasiId != null){
			LokasiSurvey lokasi = ls.findOne(lokasiId);
			if(lokasi != null){
				entity.setIdLokasi(lokasi);
			}
		}
		//entity.setDateUpdate(new Date());
		vd.save(entity);
		return entity;
	}
	
	public LaporanMingguan findById(String id){
		return vd.findById(id);
	}
        
        public List<LaporanMingguan> findByIdLokasiNLaporanKe(String idLokasi, Integer laporanKe){
            return vd.findByIdLokasiNLaporanKe(idLokasi, laporanKe);
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
