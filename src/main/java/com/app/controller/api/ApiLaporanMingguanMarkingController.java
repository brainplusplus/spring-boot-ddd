/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.api;

import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanMarking;
import com.app.object.ApiMessageCode;
import com.app.object.MultiPartObject;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.api.request.laporanmingguan.ApiLaporanMingguanGetListRequest;
import com.app.object.api.request.laporanmingguan.ApiLaporanMingguanImagesGetListRequest;
import com.app.service.api.ApiCommonService;
import com.app.service.api.ApiLaporanMingguanService;
import com.app.service.master.LaporanMingguanImagesService;
import com.app.service.master.LaporanMingguanMarkingService;
import com.app.util.AssetUtil;
import java.util.UUID;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brainplusplus
 */
@RestController
@RequestMapping("/api/laporan_mingguan_marking")
public class ApiLaporanMingguanMarkingController {
    
    @Autowired
    private ApiCommonService acs;
    
    @Autowired
    private AssetUtil assetUtil;
    
    @Autowired
    private LaporanMingguanImagesService vs;
    
    @Autowired
    private LaporanMingguanMarkingService lmms;
    
    @Autowired
    private ApiLaporanMingguanService gls;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam("idLaporan") String idLaporan,
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
            
            ApiLaporanMingguanImagesGetListRequest req = new ApiLaporanMingguanImagesGetListRequest();
            req.appId = appId;
            req.idLaporan = idLaporan;
            req.page = page;
            req.rows = rows;
            req.token = token;
            Page<LaporanMingguanMarking> pageData = gls.getImageMarkingListByIdLokasi(req);
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
	public ResponseObject save(HttpServletRequest request,@RequestParam(value = "laporanId", required = true) String laporanId,
			@RequestParam(value = "content", required = false) String file
                        ,@RequestParam(value = "token", required = false) String token,
                        @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
		try {
                        if(!acs.isValidRegAppId(appId)){
                            response.messageCode = ApiMessageCode.WRONG_APP_ID;
                            throw new Exception("Not valid App ID");
                        }
                        
                        if(!acs.isValidToken(token)){
                            response.messageCode = ApiMessageCode.WRONG_TOKEN;
                            throw new Exception("Not valid token");
                        }
                        
                        MultiPartObject image = new MultiPartObject();
                        image.name = UUID.randomUUID().toString()+".jpg";
                        image.file = assetUtil.convertBase64ToInputStream(file);
			LaporanMingguanMarking k = new LaporanMingguanMarking();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = lmms.save(k, laporanId, image);
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
	public ResponseObject update(HttpServletRequest request,@RequestParam(value = "laporanId", required = true) String laporanId,
			@RequestParam(value = "content", required = false) String file
                        ,@RequestParam(value = "token", required = false) String token,
                        @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
		try {
                    
                        if(!acs.isValidRegAppId(appId)){
                            response.messageCode = ApiMessageCode.WRONG_APP_ID;
                            throw new Exception("Not valid App ID");
                        }
                        
                        if(!acs.isValidToken(token)){
                            response.messageCode = ApiMessageCode.WRONG_TOKEN;
                            throw new Exception("Not valid token");
                        }
                        
			LaporanMingguanMarking k = new LaporanMingguanMarking();
			BeanUtils.populate(k, request.getParameterMap());
                        MultiPartObject image = new MultiPartObject();
                         if (file != null) {
                            image.name = UUID.randomUUID().toString()+".jpg";
                            image.file = assetUtil.convertBase64ToInputStream(file);
                        }else{
                             image = null;
                         }
                         response.data = lmms.update(k, laporanId, image);
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
	public ResponseObject findOne(@PathVariable("id") String id
                        ,@RequestParam(value = "token", required = false) String token,
                        @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
		try {
                        if(!acs.isValidRegAppId(appId)){
                            response.messageCode = ApiMessageCode.WRONG_APP_ID;
                            throw new Exception("Not valid App ID");
                        }
                        
                        if(!acs.isValidToken(token)){
                            response.messageCode = ApiMessageCode.WRONG_TOKEN;
                            throw new Exception("Not valid token");
                        }
			response.data = lmms.findOne(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseObject remove(@PathVariable("id") String id
                        ,@RequestParam(value = "token", required = false) String token,
                        @RequestParam(value = "appId", required = false) String appId) {
		ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
		try {
                        if(!acs.isValidRegAppId(appId)){
                            response.messageCode = ApiMessageCode.WRONG_APP_ID;
                            throw new Exception("Not valid App ID");
                        }
                        
                        if(!acs.isValidToken(token)){
                            response.messageCode = ApiMessageCode.WRONG_TOKEN;
                            throw new Exception("Not valid token");
                        }
			lmms.remove(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
}
