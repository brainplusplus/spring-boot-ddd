/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.rest.master;

import com.app.entity.LokasiSurvey;
import com.app.entity.Role;
import com.app.entity.UserApp;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.LokasiService;
import com.app.service.master.RoleService;
import com.app.service.master.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Dimas <ryan.hawari at gmail.com>
 */
@RestController
@RequestMapping("/rest/master/master_lokasi_survey")
public class MasterLokasiRestController {

    @Autowired
    private LokasiService ls;
    
    @Autowired
    private UserService us;
    
    @Autowired
    private RoleService rs;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseListObject search(@RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "tahun", required = false) String tahun,
            Principal principal) {
        ResponseListObject response = new ResponseListObject();
        try {
            UserApp user = us.getUserByUsername(principal.getName());
            Role role = rs.findOne(user.getRole().getId());
            String typeUser = role.getNama();
            String userId = user.getId();
            
            Page<LokasiSurvey> pageData;
            pageData = ls.searchLokasi(rows, page - 1, search, tahun, userId, typeUser);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = ls.count();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject save(HttpServletRequest request,
            @RequestParam(value = "prov_kode", required = true) String prov,
            @RequestParam(value = "kab_kode", required = true) String kab,
            @RequestParam(value = "paket_id", required = true) String paket, Principal principal,
            @RequestParam(value = "gambar_rencana", required = false) MultipartFile image,
            @RequestParam(value = "kurva_s_rencana", required = false) MultipartFile kurva) {
        ResponseObject response = new ResponseObject();
        try {
            LokasiSurvey k = new LokasiSurvey();
            DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
            BeanUtils.populate(k, request.getParameterMap());
            response.data = ls.save(k, prov, kab, paket, principal, image, kurva);
            response.isSuccess = true;
        } catch (Exception ex) {
            response = new ResponseObject();
            response.isSuccess = false;
            response.message = ex.getMessage();
            //ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject update(HttpServletRequest request,
            @RequestParam(value = "prov_kode", required = true) String prov,
            @RequestParam(value = "kab_kode", required = true) String kab,
            @RequestParam(value = "paket_id", required = true) String paket, Principal principal,
            @RequestParam(value = "rencana_gambar", required = false) MultipartFile image,
            @RequestParam(value = "rencana_kurva_s", required = false) MultipartFile kurva) {
        ResponseObject response = new ResponseObject();
        try {
            LokasiSurvey k = new LokasiSurvey();
            DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
            BeanUtils.populate(k, request.getParameterMap());
            response.data = ls.update(k, prov, kab, paket, principal, image, kurva);
            response.isSuccess = true;
        } catch (Exception ex) {
            response = new ResponseObject();
            response.isSuccess = false;
            response.message = ex.getMessage();
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject findOne(@PathVariable("id") String id) {
        ResponseObject<LokasiSurvey> response = new ResponseObject<LokasiSurvey>();
        try {
            response.data = ls.findOne(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(@PathVariable("id") String id) {
        ResponseObject<LokasiSurvey> response = new ResponseObject<LokasiSurvey>();
        try {
            ls.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

}
