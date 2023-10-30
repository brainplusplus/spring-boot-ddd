package com.app.service.master;

import com.app.dao.GambarLokasiDao;
import com.app.entity.LokasiImages;
import com.app.entity.LokasiSurvey;
import com.app.object.MultiPartObject;
import com.app.object.UploadImageObject;
import com.app.service.GenericDAOService;
import com.app.util.AssetUtil;
import com.app.util.CommonUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.collections.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GambarLokasiService extends GenericDAOService<GambarLokasiDao, LokasiImages> {

    @Autowired
    private AssetUtil au;

    @Autowired
    private GambarLokasiDao gld;

    @Autowired
    private LokasiService ls;

    public Page<LokasiImages> searchInt(int rows, int page, Integer search, String lokasiId) {
        search = search == null ? 0 : search;
        PageRequest paginationRequest = new PageRequest(page, rows);
        Page<LokasiImages> hasil = null;
        if (search != 0 && !CommonUtil.isEmpty(lokasiId)) {
            hasil = gld.searchPageByAllColumnLike(search, lokasiId, paginationRequest);
        } else if (search == 0 && !CommonUtil.isEmpty(lokasiId)) {
            hasil = gld.searchPageByLokasiId(lokasiId, paginationRequest);
        }
        return hasil;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public LokasiImages save(LokasiImages k, Integer type, String lokasiId, MultiPartObject gambar) {
        k.setId(UUID.randomUUID().toString());
        if (lokasiId != "" || lokasiId != null) {
            LokasiSurvey lokasi = ls.findOne(lokasiId);
            if (lokasi != null) {
                k.setIdLokasi(lokasi);
            }
        }
        UploadImageObject gbrUpload = new UploadImageObject();
        if (gambar != null) {
            try {
                gbrUpload = au.saveImage(gambar.name,gambar.file);
                k.setFile(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        k.setType(type);
        gld.save(k);
        return k;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public LokasiImages update(LokasiImages k, Integer type, String lokasiId, MultiPartObject gambar) throws IllegalAccessException, InvocationTargetException {
        LokasiImages entity = (LokasiImages) gld.findOne(k.getId());
        String gbr = entity.getFile();
        BeanUtils.populate(entity, new BeanMap(k));
        if (lokasiId != "" || lokasiId != null) {
            LokasiSurvey lokasi = ls.findOne(lokasiId);
            if (lokasi != null) {
                entity.setIdLokasi(lokasi);
            }
        }

        UploadImageObject gbrUpload = new UploadImageObject();
        if (gambar != null) {
            try {
                gbrUpload = au.saveImage(gambar.name,gambar.file);
                entity.setFile(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entity.setFile(gbr);
        }

        entity.setType(type);
        gld.save(entity);
        return entity;
    }
    
    public List<LokasiImages> findByIdLokasi(String idLokasi){
    	return gld.getAllByIdLokasi(idLokasi);
    }
}
