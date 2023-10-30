/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.rest.master;

import com.app.entity.MasterSatker;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.MasterSatkerService;
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
@RequestMapping("/rest/master/master_satker")
public class MasterSatkerRestController {

    @Autowired
    private MasterSatkerService mss;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<MasterSatker> pageData = mss.search(rows, page - 1, search);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = mss.count();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject save(HttpServletRequest request) {
        ResponseObject response = new ResponseObject();
        try {
            MasterSatker obj = new MasterSatker();
            BeanUtils.populate(obj, request.getParameterMap());
            response.isSuccess = true;
            response.data = mss.save(obj);
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
    public ResponseObject update(HttpServletRequest request) {
        ResponseObject response = new ResponseObject();
        try {
            MasterSatker obj = new MasterSatker();
            BeanUtils.populate(obj, request.getParameterMap());
            response.message = "Data updated successfully.";
            response.data = mss.update(obj);
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
        ResponseObject<MasterSatker> response = new ResponseObject<MasterSatker>();
        try {
            response.isSuccess = true;
            response.data = mss.findOne(id);
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(@PathVariable("id") String id) {
        ResponseObject<MasterSatker> response = new ResponseObject<MasterSatker>();
        try {
            mss.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

}
