/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.api;

import com.app.entity.LaporanMingguan;
import com.app.entity.LokasiImages;
import com.app.object.ApiMessageCode;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.api.request.laporanmingguan.ApiLaporanMingguanGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageGetListRequest;
import com.app.service.api.ApiCommonService;
import com.app.service.api.ApiLaporanMingguanService;
import com.app.service.api.ApiLokasiService;
import com.app.service.master.LaporanMingguanService;
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

/**
 *
 * @author brainplusplus
 */
@RestController
@RequestMapping("/api/laporan_mingguan")
public class ApiLaporanMingguanController {
    
    @Autowired
    private ApiCommonService acs;
    
    @Autowired
    private LaporanMingguanService vs;
    
    @Autowired
    private ApiLaporanMingguanService gls;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam("idLokasi") String idLokasi,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseListObject response = new ResponseListObject();
        try {
            if(!acs.isValidRegAppId(appId)){
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }
                        
            if(!acs.isValidToken(token)){
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            
            ApiLaporanMingguanGetListRequest req = new ApiLaporanMingguanGetListRequest();
            req.appId = appId;
            req.idLokasi = idLokasi;
            req.page = page;
            req.rows = rows;
            req.token = token;
            Page<LaporanMingguan> pageData = gls.getListByIdLokasi(req);
            if(pageData!=null){
                response.data = pageData.getContent();
                response.recordsFiltered = pageData.getTotalElements();
                response.recordsTotal = gls.count();
                response.isSuccess = true;
            }else{
                throw new Exception("Not found data");
            }
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseObject save(HttpServletRequest request,@RequestParam(value = "lokasiId", required = true) String lokasiId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
                    
                    if(!acs.isValidRegAppId(appId)){
                        response.messageCode = ApiMessageCode.WRONG_APP_ID;
                        throw new Exception("Not valid App ID");
                    }

                    if(!acs.isValidToken(token)){
                        response.messageCode = ApiMessageCode.WRONG_TOKEN;
                        throw new Exception("Not valid token");
                    }
			LaporanMingguan k = new LaporanMingguan();
			DateConverter dateConverter = new DateConverter();
                        dateConverter.setPattern("yyyy-MM-dd");
                        ConvertUtils.register(dateConverter, java.util.Date.class);
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.save(k, lokasiId);
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
	public ResponseObject update(HttpServletRequest request,@RequestParam(value = "lokasiId", required = true) String lokasiId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
                    if(!acs.isValidRegAppId(appId)){
                        response.messageCode = ApiMessageCode.WRONG_APP_ID;
                        throw new Exception("Not valid App ID");
                    }

                    if(!acs.isValidToken(token)){
                        response.messageCode = ApiMessageCode.WRONG_TOKEN;
                        throw new Exception("Not valid token");
                    }
			LaporanMingguan k = new LaporanMingguan();
			DateConverter dateConverter = new DateConverter();
                        dateConverter.setPattern("yyyy-MM-dd");
                        ConvertUtils.register(dateConverter, java.util.Date.class);
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.update(k, lokasiId);
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
	public ResponseObject findOne(@PathVariable("id") String id,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
                    
                    if(!acs.isValidRegAppId(appId)){
                        response.messageCode = ApiMessageCode.WRONG_APP_ID;
                        throw new Exception("Not valid App ID");
                    }

                    if(!acs.isValidToken(token)){
                        response.messageCode = ApiMessageCode.WRONG_TOKEN;
                        throw new Exception("Not valid token");
                    }
                    
			response.data = vs.findOne(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseObject remove(@PathVariable("id") String id,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
                    if(!acs.isValidRegAppId(appId)){
                        response.messageCode = ApiMessageCode.WRONG_APP_ID;
                        throw new Exception("Not valid App ID");
                    }

                    if(!acs.isValidToken(token)){
                        response.messageCode = ApiMessageCode.WRONG_TOKEN;
                        throw new Exception("Not valid token");
                    }
			vs.remove(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
}
