/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.master;

import com.app.dao.KabupatenDao;
import com.app.dao.LokasiDao;
import com.app.dao.PaketDao;
import com.app.dao.ProvinsiDao;
import com.app.entity.Kab;
import com.app.entity.LokasiSurvey;
import com.app.entity.Paket;
import com.app.entity.Prov;
import com.app.entity.UserApp;
import com.app.object.UploadImageObject;
import com.app.service.GenericDAOService;
import com.app.util.AssetUtil;
import com.app.util.CommonUtil;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
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

/**
 *
 * @author dimasrh
 */
@Service
public class LokasiService extends GenericDAOService<LokasiDao, LokasiSurvey> {

    @Autowired
    private AssetUtil au;

    @Autowired
    private ProvinsiDao pvd;

    @Autowired
    private KabupatenDao kd;

    @Autowired
    private PaketDao pd;

    @Autowired
    private LokasiDao ld;

    @Autowired
    private UserService userService;

    public Page<LokasiSurvey> searchLokasi(int rows, int page, String search, String tahun, String user, String userType) {
//        System.out.println("seacrh Lokasi param");
//        System.out.println(tahun);
//        System.out.println(search);
        PageRequest paginationRequest = new PageRequest(page, rows);
        Page<LokasiSurvey> hasil = null;
        if (CommonUtil.isEmpty(search) && CommonUtil.isEmpty(tahun)) {
//            System.out.println("userType masuk 1");
            if (userType.equals("PENGAWAS")) {
//                System.out.println("userType masuk 1 epngawas");
                hasil = ld.searchPageByUserPengawas(user, paginationRequest);
            } else if (userType.equals("REKANAN")) {
//                System.out.println("userType masuk 1 rekanan");
                hasil = ld.searchPageByUserRekanan(user, paginationRequest);
            } else {
//                System.out.println("userType masuk 1 admin");
                hasil = ld.findAll(paginationRequest);
            }
        } else if (!CommonUtil.isEmpty(search) || !CommonUtil.isEmpty(tahun)) {
//            System.out.println("userType masuk 2");
            if (userType.equals("PENGAWAS")) {
//                System.out.println("userType masuk 2 pengawas");
                hasil = ld.searchPageByPengawasAndAllColumnAndTahunLike(user, "%" + search + "%", tahun, paginationRequest);
            } else if (userType.equals("REKANAN")) {
//                System.out.println("userType masuk 2 REKANAN");
                hasil = ld.searchPageByRekananAndAllColumnAndTahunLike(user, "%" + search + "%", tahun, paginationRequest);
            } else {
//                System.out.println("userType masuk 2 ADMIN");
                hasil = ld.searchPageByAllColumnAndTahunLike("%" + search + "%", tahun, paginationRequest);
            }
        }
//        System.out.println(hasil);
        return hasil;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public LokasiSurvey save(LokasiSurvey k, String prov, String kab, String paket, Principal principal, MultipartFile gambar, MultipartFile kurva) throws ParseException {
        k.setId(UUID.randomUUID().toString());

        UploadImageObject gbrUpload = new UploadImageObject();
        if (gambar != null) {
            try {
                gbrUpload = au.saveImage(gambar);
                k.setGambarRencana(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String kurvaUpload;
        try {
            kurvaUpload = au.saveFile(kurva);
            k.setKurvaSRencana(kurvaUpload.toString());
        } catch (Exception ex) {
            Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!CommonUtil.isEmpty(prov)) {
            Prov pv = pvd.findByKode(prov);
            k.setKodeProv(pv);
        }
        if (!CommonUtil.isEmpty(kab)) {
            Kab kb = kd.findByKode(kab);
            k.setKodeKab(kb);
        }
        if (!CommonUtil.isEmpty(paket)) {
            Paket p = pd.findOne(paket);
            k.setIdPaket(p);
        }
        UserApp user = userService.getUserByUsername(principal.getName());
        k.setIdUser(user);
        ld.save(k);
        return k;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public LokasiSurvey update(LokasiSurvey k, String prov, String kab, String paket, Principal principal, MultipartFile gambar, MultipartFile kurva) throws IllegalAccessException, InvocationTargetException {
        LokasiSurvey entity = ld.findOne(k.getId());
        String gbrRencana = entity.getGambarRencana();
        String krvRencana = entity.getKurvaSRencana();
        BeanUtils.populate(entity, new BeanMap(k));
        if (gambar != null) {
            UploadImageObject gbrUpload = new UploadImageObject();
            try {
                gbrUpload = au.saveImage(gambar);
                entity.setGambarRencana(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entity.setGambarRencana(gbrRencana);
        }

        if (kurva != null) {
            String kurvaUpload;
            try {
                kurvaUpload = au.saveFile(kurva);
                entity.setKurvaSRencana(kurvaUpload.toString());
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entity.setKurvaSRencana(krvRencana);
        }

        if (!CommonUtil.isEmpty(prov)) {
            Prov pv = pvd.findByKode(prov);
            entity.setKodeProv(pv);
        }
        if (!CommonUtil.isEmpty(kab)) {
            Kab kb = kd.findByKode(kab);
            entity.setKodeKab(kb);
        }
        if (!CommonUtil.isEmpty(paket)) {
            Paket p = pd.findOne(paket);
            entity.setIdPaket(p);
        } else {
            entity.setIdPaket(null);
        }
        UserApp user = userService.getUserByUsername(principal.getName());
        entity.setIdUser(user);
        ld.save(entity);
        return entity;
    }

    public LokasiSurvey findById(String id) {
        return ld.findById(id);
    }

    public Page<LokasiSurvey> findAllByIdUser(String idUser, int page, int rows) {
        PageRequest paginationRequest = new PageRequest(page, rows);
        return ld.getAllByIdUser(idUser, paginationRequest);
    }

    public List<LokasiSurvey> findAll() {
        return ld.getAll();//IteratorUtils.toList(ld.findAll().iterator());    	
    }

    public Page<LokasiSurvey> findAll(int page, int rows) {
        PageRequest paginationRequest = new PageRequest(page, rows);
        return ld.getAll(paginationRequest);//IteratorUtils.toList(ld.findAll().iterator());    	
    }

    public List<LokasiSurvey> findByIdPaketAndKodeProv(ArrayList paket, String kodeProv) {
        return ld.findByIdPaketAndKodeProv(paket, kodeProv);
    }

    public List<LokasiSurvey> findByIdPaketAndKodeKab(ArrayList paket, String kodeKab) {
        return ld.findByIdPaketAndKodeKab(paket, kodeKab);
    }

    public List<LokasiSurvey> findByKodeProv(String kodeProv) {
        return ld.findByKodeProv(kodeProv);
    }

    public List<LokasiSurvey> findByKodeKab(String kodeKab) {
        return ld.findByKodeKab(kodeKab);
    }
}
