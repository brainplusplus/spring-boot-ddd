package com.app.service.master;

import com.app.dao.RealisasiDao;
import com.app.entity.LokasiSurvey;
import com.app.entity.SerapanRealisasiEntity;
import com.app.service.GenericDAOService;
import com.app.util.CommonUtil;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.UUID;

@Service
public class SerapanRealisasiService extends GenericDAOService<RealisasiDao, SerapanRealisasiEntity> {

    @Autowired
    private RealisasiDao realisasiDao;

    @Autowired
    private LokasiService lokasiSurvey;

    /**
     * Search paket is given
     */
    public Page<SerapanRealisasiEntity> searchPaket(int rows, int page, String search) {
        PageRequest paginationRequest = new PageRequest(page, rows);
        Page<SerapanRealisasiEntity> hasil = null;

        if (CommonUtil.isEmpty(search)) {
            hasil = realisasiDao.findAll(paginationRequest);
        } else if (!CommonUtil.isEmpty(search)) {
            hasil = realisasiDao.searchPageByAllColumnLike("%" + search + "%", paginationRequest);
        }

        return hasil;
    }

    /**
     * Save Model
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public SerapanRealisasiEntity save(SerapanRealisasiEntity realisasiEntity, String lokasiId) throws ParseException {
        realisasiEntity.setId(UUID.randomUUID().toString());

        /*
         * Set FKs key
         */
        LokasiSurvey lokasi = lokasiSurvey.findOne(lokasiId);
        realisasiEntity.setIdLokasi(lokasi);

        realisasiDao.save(realisasiEntity);

        return realisasiEntity;
    }

    /**
     * Update Model
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public SerapanRealisasiEntity update(SerapanRealisasiEntity serapanRealisasiEntityVar, String dataId) throws IllegalAccessException, InvocationTargetException {
        DozerBeanMapper dozenBeanMapper = new DozerBeanMapper();
        SerapanRealisasiEntity serapanRealisasiEntity = realisasiDao.findOne(dataId);

        // load default
        LokasiSurvey surveyId = serapanRealisasiEntity.getIdLokasi();

        // put POST data to mapper
        dozenBeanMapper.map(serapanRealisasiEntityVar, serapanRealisasiEntity);

        // set default value to mapper
        serapanRealisasiEntity.setId(dataId);
        serapanRealisasiEntity.setIdLokasi(surveyId);

        // save it
        realisasiDao.save(serapanRealisasiEntity);

        return serapanRealisasiEntity;
    }

    public Page<SerapanRealisasiEntity> searchDataByIndex(int rows, int page, String search, String lokasiId) {
        PageRequest paginationRequest = new PageRequest(page, rows);

        search = (search == null) ? "" : search;
        Page<SerapanRealisasiEntity> hasil = null;

        if (!CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(lokasiId)) {
            hasil = realisasiDao.searchPageByAllColumnLike(search, lokasiId, paginationRequest);
        } else if (CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(lokasiId)) {
            hasil = realisasiDao.searchPageByLokasiId(lokasiId, paginationRequest);
        }

        return hasil;
    }
}