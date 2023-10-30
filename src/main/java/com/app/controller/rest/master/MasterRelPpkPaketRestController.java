/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.controller.rest.master;

import com.app.entity.RelPpkPaket;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.RelPpkPaketService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author dimasrh <ryan.hawari at gmail.com>
 */
@RestController
@RequestMapping("rest/master/master_rel_ppk_paket")
public class MasterRelPpkPaketRestController {
    
    @Autowired
    private RelPpkPaketService rpps;
    
    @RequestMapping (value="/search", method=RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search){
        ResponseListObject response = new ResponseListObject();
        try {
            Page<RelPpkPaket> pageData = rpps.search(rows, page - 1, search);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = rpps.count();
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
            @RequestParam(value = "paket_id", required = true) String paket,
            @RequestParam(value = "user_id", required = true) String user) {
        ResponseObject response = new ResponseObject();
        try {
            RelPpkPaket obj = new RelPpkPaket();
            BeanUtils.populate(obj, request.getParameterMap());
            response.isSuccess = true;
            response.data = rpps.save(obj, paket, user);
            response.message = "Data saved successfully.";
        } catch (Exception ex) {
            response = new ResponseObject();
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject update(HttpServletRequest request,
            @RequestParam(value = "paket_id", required = true) String paket,
            @RequestParam(value = "user_id", required = true) String user) {
        ResponseObject response = new ResponseObject();
        try {
            RelPpkPaket obj = new RelPpkPaket();
            BeanUtils.populate(obj, request.getParameterMap());
            response.message = "Data updated successfully.";
            response.data = rpps.update(obj, paket, user);
            response.isSuccess = true;
        } catch (Exception ex) {
            response = new ResponseObject();
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject findOne(@PathVariable("id") String id) {
        ResponseObject<RelPpkPaket> response = new ResponseObject<RelPpkPaket>();
        try {
            response.isSuccess = true;
            response.data = rpps.findOne(id);
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(@PathVariable("id") String id) {
        ResponseObject<RelPpkPaket> response = new ResponseObject<RelPpkPaket>();
        try {
            rpps.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

}
