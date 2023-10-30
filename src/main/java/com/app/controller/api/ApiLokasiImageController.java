/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.api;

import com.app.entity.LokasiImages;
import com.app.object.ApiMessageCode;
import com.app.object.MultiPartObject;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.api.request.lokasi.ApiLokasiImageGetListRequest;
import com.app.object.api.request.lokasi.ApiLokasiImageSaveRequest;
import com.app.service.api.ApiCommonService;
import com.app.service.api.ApiLokasiService;
import com.app.service.master.GambarLokasiService;
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

/**
 *
 * @author brainplusplus
 */
@RestController
@RequestMapping("/api/lokasi_image")
public class ApiLokasiImageController {

    @Autowired
    private ApiCommonService acs;

    @Autowired
    private AssetUtil assetUtil;

    @Autowired
    private ApiLokasiService als;

    @Autowired
    private GambarLokasiService gls;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam("idLokasi") String idLokasi,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseListObject response = new ResponseListObject();
        try {
            ApiLokasiImageGetListRequest req = new ApiLokasiImageGetListRequest();
            req.appId = appId;
            req.idLokasi = idLokasi;
            req.page = page;
            req.rows = rows;
            req.token = token;
            Page<LokasiImages> pageData = als.getImageListByIdLokasi(req);
            if (pageData != null) {
                response.data = pageData.getContent();
                response.recordsFiltered = pageData.getTotalElements();
                response.recordsTotal = gls.count();
                response.isSuccess = true;
            } else {
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
    public ResponseObject save(HttpServletRequest request,
            @RequestParam(value = "lokasiId", required = true) String idLokasi,
            @RequestParam(value = "file_gambar", required = false) String file,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            if (!acs.isValidRegAppId(appId)) {
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }

            if (!acs.isValidToken(token)) {
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            LokasiImages k = new LokasiImages();
            Integer type = Integer.parseInt(request.getParameter("type"));
            BeanUtils.populate(k, request.getParameterMap());
            ApiLokasiImageSaveRequest req = new ApiLokasiImageSaveRequest();

            MultiPartObject image = new MultiPartObject();
            image.name = UUID.randomUUID().toString() + ".jpg";
            image.file = assetUtil.convertBase64ToInputStream(file);

            req.appId = appId;
            req.idLokasi = idLokasi;
            req.image = image;
            req.lokasiImage = k;
            req.token = token;
            req.type = type;
            response.data = als.save(req);
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
            @RequestParam(value = "lokasiId", required = true) String idLokasi,
            @RequestParam(value = "file_gambar", required = false) String file,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            if (!acs.isValidRegAppId(appId)) {
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }

            if (!acs.isValidToken(token)) {
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            LokasiImages k = new LokasiImages();
            Integer type = Integer.parseInt(request.getParameter("type"));
            BeanUtils.populate(k, request.getParameterMap());
            ApiLokasiImageSaveRequest req = new ApiLokasiImageSaveRequest();

            MultiPartObject image = new MultiPartObject();
            if (file != null) {
                image.name = UUID.randomUUID().toString() + ".jpg";
                image.file = assetUtil.convertBase64ToInputStream(file);
            } else {
                image = null;
            }

            //req.appId = appId;
            req.idLokasi = idLokasi;
            req.image = image;
            req.lokasiImage = k;
           // req.token = token;
            req.type = type;
            //response.data = als.update(req);
            response.data = gls.update(req.lokasiImage, req.type, req.idLokasi, req.image);
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
    public ResponseObject findOne(
            @PathVariable("id") String id,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            if (!acs.isValidRegAppId(appId)) {
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }

            if (!acs.isValidToken(token)) {
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            response.data = gls.findOne(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(
            @PathVariable("id") String id,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            if (!acs.isValidRegAppId(appId)) {
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }

            if (!acs.isValidToken(token)) {
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            gls.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
}
