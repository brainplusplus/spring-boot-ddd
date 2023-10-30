package com.app.controller.rest.master;

import com.app.entity.LokasiImages;
import com.app.object.MultiPartObject;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.GambarLokasiService;
import com.app.util.CommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/master/master_gambar_lokasi")
public class MasterGambarLokasiRestController {

    @Autowired
    private GambarLokasiService gls;

    @RequestMapping(value = "/search/{lokasi_id}", method = RequestMethod.GET)
    public ResponseListObject search(
            @PathVariable("lokasi_id") String lokasiId,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) Integer search) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<LokasiImages> pageData = gls.searchInt(rows, page - 1, search, lokasiId);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = gls.count();
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
            @RequestParam(value = "lokasiId", required = true) String lokasiId,
            @RequestParam(value = "file_gambar", required = false) MultipartFile file) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            LokasiImages k = new LokasiImages();
            Integer type = Integer.parseInt(request.getParameter("type"));
            BeanUtils.populate(k, request.getParameterMap());
            MultiPartObject image = new MultiPartObject();
            image.file = file.getInputStream();
            image.name = file.getOriginalFilename();
            response.data = gls.save(k, type, lokasiId, image);
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
            @RequestParam(value = "lokasiId", required = true) String lokasiId,
            @RequestParam(value = "file_gambar", required = false) MultipartFile file) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            LokasiImages k = new LokasiImages();
            Integer type = Integer.parseInt(request.getParameter("type"));
            BeanUtils.populate(k, request.getParameterMap());
            MultiPartObject image = new MultiPartObject();
            if (file!=null) {
                image.file = file.getInputStream();
                image.name = file.getOriginalFilename();
            }else{
                image = null;
            }
            response.data = gls.update(k, type, lokasiId, image);
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
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
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
    public ResponseObject remove(@PathVariable("id") String id) {
        ResponseObject<LokasiImages> response = new ResponseObject<LokasiImages>();
        try {
            gls.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
}
