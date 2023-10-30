package com.app.controller.rest.master;

import com.app.entity.UserApp;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.UserService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by driyanh on 06/11/2015.
 */
@RestController
@RequestMapping("/rest/master/master_user")
public class MasterUserRestController {

    @Autowired
    private UserService us;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "role_id", required = false) String roleId) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<UserApp> pageData = us.searchUser(rows, page - 1, search, roleId);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = us.count();
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
            @RequestParam(value = "role_combo", required = true) String roleId,
            @RequestParam(value = "wilayah_combo", required = true) String wilayah,
            @RequestParam(value = "logo_upload", required = true) MultipartFile logo) {
        ResponseObject response = new ResponseObject();
        try {
            DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
            UserApp k = new UserApp();
            BeanUtils.populate(k, request.getParameterMap());
            response.data = us.save(k, roleId, wilayah, logo);
            response.isSuccess = true;
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
            @RequestParam(value = "role_combo", required = true) String roleId,
            @RequestParam(value = "wilayah_combo", required = true) String wilayah,
            @RequestParam(value = "logo_upload", required = false) MultipartFile logo) {
        ResponseObject response = new ResponseObject();
        try {
            DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
            UserApp k = new UserApp();
            BeanUtils.populate(k, request.getParameterMap());
            response.data = us.update(k, roleId, wilayah, logo);
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
        ResponseObject<UserApp> response = new ResponseObject<UserApp>();
        try {
            response.data = us.findOne(id);
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
        ResponseObject<UserApp> response = new ResponseObject<UserApp>();
        try {
            us.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

}
