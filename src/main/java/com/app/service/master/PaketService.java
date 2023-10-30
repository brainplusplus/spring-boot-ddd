package com.app.service.master;

import com.app.dao.PaketDao;
import com.app.util.CommonUtil;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.app.entity.Paket;
import com.app.service.GenericDAOService;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.UUID;

@Service
public class PaketService extends GenericDAOService<PaketDao, Paket> {

    @Autowired
    private PaketDao paketDao;

    /**
     * Search paket is given
     */
    public Page<Paket> searchPaket(int rows, int page, String search, String tahun)
    {
        PageRequest paginationRequest = new PageRequest(page, rows);
        Page<Paket> hasil = null;
//        System.out.println("Paket Tahun");
//        System.out.println(tahun);
        if (CommonUtil.isEmpty(search)&&CommonUtil.isEmpty(tahun)) {
//            System.out.println("Paket Tahun gak ada");
            hasil = paketDao.findAll(paginationRequest);
        } else if (!CommonUtil.isEmpty(search)||!CommonUtil.isEmpty(tahun)) {
//            System.out.println("Paket Tahun ada");
            if(!CommonUtil.isEmpty(tahun)){
                hasil = paketDao.searchPageByTahunAndAllColumnLike("%" + search + "%", tahun, paginationRequest);
            }else{
                hasil = paketDao.searchPageByAllColumnLike("%" + search + "%", paginationRequest);
            }
        }

        return hasil;
    }

    /**
     * Save Model
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Paket save(Paket paketVar) throws ParseException
    {
        paketVar.setId(UUID.randomUUID().toString());
        paketDao.save(paketVar);

        return paketVar;
    }

    /**
     * Update Model
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Paket update(Paket paketVar) throws IllegalAccessException, InvocationTargetException
    {
        DozerBeanMapper dozenBeanMapper = new DozerBeanMapper();
        Paket paketModel = paketDao.findOne(paketVar.getId());
        dozenBeanMapper.map(paketVar, paketModel);
        paketDao.save(paketModel);

        return paketModel;
    }
}