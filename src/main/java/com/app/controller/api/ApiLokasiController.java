/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller.api;

import com.app.entity.LokasiSurvey;
import com.app.entity.Role;
import com.app.entity.SessionApp;
import com.app.entity.UserApp;
import com.app.object.ApiMessageCode;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.api.request.lokasi.ApiLokasiGetListRequest;
import com.app.service.api.ApiCommonService;
import com.app.service.api.ApiLokasiService;
import com.app.service.master.LokasiService;
import com.app.service.master.RoleService;
import com.app.service.master.SessionAppService;
import com.app.service.master.UserService;
import com.app.util.CommonUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brainplusplus
 */
@RestController
@RequestMapping("/api/lokasi")
public class ApiLokasiController {

    @Autowired
    private ApiCommonService acs;

    @Autowired
    private ApiLokasiService aus;

    @Autowired
    private SessionAppService sas;

    @Autowired
    private LokasiService ls;

    @Autowired
    private UserService us;

    @Autowired
    private RoleService rs;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseObject list(HttpServletRequest request,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "appId", required = false) String appId) {
        ApiLokasiGetListRequest req = new ApiLokasiGetListRequest();
        req.token = token;
        req.appId = appId;
        req.page = page;
        req.rows = rows;

        ResponseListObject response = new ResponseListObject();
        try {
            Page<LokasiSurvey> result = aus.getList(req);
            response.data = result.getContent();
            response.recordsFiltered = result.getTotalElements();
            response.recordsTotal = aus.count();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/listByIdUser", method = RequestMethod.GET)
    public ResponseObject listByIdUser(HttpServletRequest request, @RequestParam(value = "idUser", required = false) String idUser,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "appId", required = false) String appId) {
        ApiLokasiGetListRequest req = new ApiLokasiGetListRequest();
        req.idUser = idUser;
        req.token = token;
        req.appId = appId;
        req.page = page;
        req.rows = rows;

        ResponseObject response = new ResponseObject();
        try {
            response.data = aus.getListByIdUser(req);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/listBySession", method = RequestMethod.GET)
    public ResponseObject listBySession(HttpServletRequest request, @RequestParam(value = "idUser", required = false) String idUser,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "appId", required = false) String appId,
            @RequestParam(value = "tahun", required = false) String tahun,
            @RequestParam(value = "search", required = false) String search) {
        ApiLokasiGetListRequest req = new ApiLokasiGetListRequest();
        req.idUser = idUser;
        req.token = token;
        req.appId = appId;
        req.page = page;
        req.rows = rows;

        ResponseObject response = new ResponseObject();

        SessionApp session = null;
        session = sas.getSessionByToken(token);

        UserApp user = us.getUserByUsername(session.getUsername());
        Role role = rs.findOne(user.getRole().getId());
        String userType = role.getNama();
        String userId = user.getId();
//        System.out.println("sessionByToken");
//        System.out.println(userId);
//        System.out.println(userType);
        if (CommonUtil.isEmpty(search)) {
            search = "";
        }
        if (CommonUtil.isEmpty(tahun)) {
            tahun = "";
        }
        try {
            if (!acs.isValidRegAppId(appId)) {
                response.messageCode = ApiMessageCode.WRONG_APP_ID;
                throw new Exception("Not valid App ID");
            }

            if (!acs.isValidToken(token)) {
                response.messageCode = ApiMessageCode.WRONG_TOKEN;
                throw new Exception("Not valid token");
            }
            //response.data = aus.getListByIdUser(req);
            Page<LokasiSurvey> lokasi = ls.searchLokasi(rows, page, search, tahun, userId, userType);
            response.data = lokasi.getContent();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
            ex.printStackTrace();
        }
        return response;
    }
}
