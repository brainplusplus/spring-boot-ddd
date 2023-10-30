package com.app.controller.rest.master;

import com.app.entity.Kab;
import com.app.entity.LaporanMingguan;
import com.app.entity.LaporanMingguanImages;
import com.app.entity.LaporanMingguanMarking;
import com.app.object.MultiPartObject;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.StringToDateConverter;
import com.app.service.master.KabupatenService;
import com.app.service.master.LaporanMingguanImagesService;
import com.app.service.master.LaporanMingguanMarkingService;
import com.app.service.master.LaporanMingguanService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/master/master_laporan_mingguan_marking")
public class MasterLaporanMingguanMarkingRestController {

    @Autowired
    private LaporanMingguanMarkingService vs;

    @RequestMapping(value = "/search/{laporanId}", method = RequestMethod.GET)
    public ResponseListObject search(
            @PathVariable("laporanId") String laporanId,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<LaporanMingguanMarking> pageData = vs.SearchByLokasi(rows, page - 1, search, laporanId);
            response.rows = pageData.getContent();
            response.recordsFiltered = pageData.getTotalElements();
            response.recordsTotal = vs.count();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject save(HttpServletRequest request, @RequestParam(value = "laporanId", required = true) String laporanId,
            @RequestParam(value = "content", required = false) MultipartFile file) {
        ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
        try {
            LaporanMingguanMarking k = new LaporanMingguanMarking();
            BeanUtils.populate(k, request.getParameterMap());
            MultiPartObject image = new MultiPartObject();
            image.file = file.getInputStream();
            image.name = file.getOriginalFilename();
            response.data = vs.save(k, laporanId, image);
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
    public ResponseObject update(HttpServletRequest request, @RequestParam(value = "laporanId", required = true) String laporanId,
            @RequestParam(value = "content", required = false) MultipartFile file) {
        ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
        try {
            LaporanMingguanMarking k = new LaporanMingguanMarking();
            BeanUtils.populate(k, request.getParameterMap());
            MultiPartObject image = new MultiPartObject();
            if (file != null) {
                image.file = file.getInputStream();
                image.name = file.getOriginalFilename();
            } else {
                image = null;
            }
            response.data = vs.update(k, laporanId, image);
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
        ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
        try {
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
    public ResponseObject remove(@PathVariable("id") String id) {
        ResponseObject<LaporanMingguanMarking> response = new ResponseObject<LaporanMingguanMarking>();
        try {
            vs.remove(id);
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
}
