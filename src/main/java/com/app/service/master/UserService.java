package com.app.service.master;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.RoleDao;
import com.app.dao.UserDao;
import com.app.dao.WilayahDao;
import com.app.entity.Role;
import com.app.entity.UserApp;
import com.app.entity.Wilayah;
import com.app.object.UploadImageObject;
import com.app.service.GenericDAOService;
import com.app.util.AssetUtil;
import com.app.util.CommonUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;

@Service
public class UserService extends GenericDAOService<UserDao, UserApp> {

    @Autowired
    private UserDao ud;

    @Autowired
    private RoleDao rd;

    @Autowired
    private WilayahDao wd;

    @Autowired
    private AssetUtil au;

    public UserApp getUserByUsername(String username) {
        return ud.findByUsername(username);
    }

    public UserApp getUserById(String id) {
        return ud.findById(id);
    }

    public Iterable<UserApp> getUserByRolePengawas() {
        return ud.findByIdRolePengawas();
    }

    public Iterable<UserApp> getUserByRoleRekanan() {
        return ud.findByIdRoleRekanan();
    }

    public Page<UserApp> searchUser(int rows, int page, String search, String roleId) {
        PageRequest paginationRequest = new PageRequest(page, rows);
        Page<UserApp> hasil = null;
        if (CommonUtil.isEmpty(search) && CommonUtil.isEmpty(roleId)) {
            hasil = ud.findAll(paginationRequest);
        } else if (!CommonUtil.isEmpty(search) && CommonUtil.isEmpty(roleId)) {
            hasil = ud.searchPageByAllColumnLike("%" + search + "%", paginationRequest);
        } else if (!CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(roleId)) {
            hasil = ud.searchPageByAllColumnLikeAndRoleId("%" + search + "%", roleId, paginationRequest);
        } else if (CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(roleId)) {
            hasil = ud.searchPageByRoleId(roleId, paginationRequest);
        }
        return hasil;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public UserApp save(UserApp k, String roleId, String wilayah, MultipartFile logo) {
        k.setId(UUID.randomUUID().toString());
        //k.setActive(true);
        if (!CommonUtil.isEmpty(roleId)) {
            Role r = rd.findOne(roleId);
            k.setRole(r);
        }
        if (!CommonUtil.isEmpty(wilayah)) {
            Wilayah w = wd.findOne(wilayah);
            k.setIdWilayah(w);
        }
        if (logo != null) {
            UploadImageObject gbrUpload = new UploadImageObject();
            try {
                gbrUpload = au.saveImage(logo);
                k.setLogo(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        k.setPassword(CommonUtil.encodeText(k.getPassword()));
        ud.save(k);
        return k;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public UserApp update(UserApp k, String roleId, String wilayah, MultipartFile logo) throws IllegalAccessException, InvocationTargetException {
        //DozerBeanMapper doz = new DozerBeanMapper();
        UserApp entity = ud.findOne(k.getId());
        String lg = entity.getLogo();
        //doz.map(k, entity);
        BeanUtils.populate(entity, new BeanMap(k));
        if (!CommonUtil.isEmpty(roleId)) {
            //System.out.println("MASUK A");
            Role r = rd.findOne(roleId);
            //System.out.println("ROLE : "+r);
            entity.setRole(r);
        } else {
            //System.out.println("MASUK B");
            entity.setRole(null);
        }
        //if (!entity.getPassword().equals(k.getPassword())) {
        if (!CommonUtil.matchesText(k.getPassword(), entity.getPassword())) {
            entity.setPassword(CommonUtil.encodeText(k.getPassword()));
        }
        if (!CommonUtil.isEmpty(wilayah)) {
            Wilayah w = wd.findOne(wilayah);
            entity.setIdWilayah(w);
        }
        if (logo != null) {
            UploadImageObject gbrUpload = new UploadImageObject();
            try {
                gbrUpload = au.saveImage(logo);
                entity.setLogo(gbrUpload.path);
            } catch (Exception ex) {
                Logger.getLogger(LokasiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entity.setLogo(lg);
        }
        //entity.setActive(true);
        ud.save(entity);
        return entity;
    }

    public boolean validLogin(String username, String password) {
        UserApp user = getUserByUsername(username);
        if (user != null) {
            return CommonUtil.matchesText(password, user.getPassword());
        } else {
            return false;
        }
    }

}
