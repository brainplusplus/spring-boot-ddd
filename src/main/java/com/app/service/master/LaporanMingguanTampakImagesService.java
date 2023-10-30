package com.app.service.master;

import com.app.dao.LaporanMingguanDao;
import com.app.dao.LaporanMingguanImagesDao;
import com.app.dao.LaporanMingguanTampakImagesDao;
import com.app.dao.ProvinsiDao;
import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanTampakImages;
import com.app.entity.Prov;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.UploadImageObject;
import com.app.util.AssetUtil;
import com.app.service.GenericDAOService;
import com.app.util.CommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class LaporanMingguanTampakImagesService extends GenericDAOService<LaporanMingguanTampakImagesDao, LaporanMingguanTampakImages> {
	
	@Autowired
	private LaporanMingguanTampakImagesDao vd;
	
	@Autowired
    private AssetUtil au;
	
	@Autowired
	private LaporanMingguanDao lmd;
	
	public Page<LaporanMingguanTampakImages> SearchByLokasi(int rows, int page, String search, String idLaporan) {
		
		PageRequest paginationRequest = new PageRequest(page, rows);
		Page<LaporanMingguanTampakImages> hasil = null;
		if(CommonUtil.isEmpty(search) || search==null){
			hasil = vd.searchPageByLaporan(idLaporan, paginationRequest);
		}else{
                    search = search == null ? "%%" :"%"+  search +"%";
                    hasil = vd.searchPageByLaporanNKet(idLaporan, search, paginationRequest);
			
		}
		return hasil;
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public LaporanMingguanTampakImages save(LaporanMingguanTampakImages k, String idLaporan, MultipartFile file){
		k.setId(UUID.randomUUID().toString());
		UploadImageObject gbrUpload = new UploadImageObject();
		if(file != null){
			try {
                gbrUpload = au.saveImage(file);
                k.setFile(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
		}
		if(idLaporan != ""|| idLaporan != null){
			LaporanMingguan laporan = lmd.findOne(idLaporan);
			if(laporan != null){
				k.setIdLaporan(laporan);
			}
		}
		return vd.save(k);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public LaporanMingguanTampakImages update(LaporanMingguanTampakImages k, String idLaporan, MultipartFile file)throws IllegalAccessException, InvocationTargetException{
		LaporanMingguanTampakImages entity = (LaporanMingguanTampakImages) vd.findOne(k.getId());
		String gbr = entity.getFile();
        BeanUtils.populate(entity, new BeanMap(k));
		UploadImageObject gbrUpload = new UploadImageObject();
		if(file != null){
			try {
                gbrUpload = au.saveImage(file);
                entity.setFile(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
		}else {
            entity.setFile(gbr);
        }
		if(idLaporan != ""|| idLaporan != null){
			LaporanMingguan laporan = lmd.findOne(idLaporan);
			if(laporan != null){
				entity.setIdLaporan(laporan);
			}
		}
		return vd.save(entity);
	}

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
