package com.app.controller.rest.master;

import com.app.entity.SerapanRealisasiEntity;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.StringToDateConverter;
import com.app.service.master.SerapanRealisasiService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/master/realisasi")
public class MasterRealisasiRestController {

    @Autowired
    private SerapanRealisasiService serapanRealisasiService;

    @RequestMapping(value = "/search/{lokasi_id}", method = RequestMethod.GET)
    public ResponseListObject search(
            @PathVariable("lokasi_id") String lokasiId,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search
    ) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<SerapanRealisasiEntity> serapanRealisasiEntities = serapanRealisasiService.searchDataByIndex(rows, page - 1, search, lokasiId);
            response.rows = serapanRealisasiEntities.getContent();
            response.recordsFiltered = serapanRealisasiEntities.getTotalElements();
            response.recordsTotal = serapanRealisasiService.count();
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/save/{lokasi_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject save(
            @PathVariable("lokasi_id") String lokasiId,
            HttpServletRequest request
    ) {
        ResponseObject<SerapanRealisasiEntity> response = new ResponseObject<SerapanRealisasiEntity>();
        try {
            SerapanRealisasiEntity serapanRealisasiEntity = new SerapanRealisasiEntity();
            ConvertUtils.register((Converter) new StringToDateConverter(StringToDateConverter.DEFAULT_DATE_PATTERN), java.util.Date.class);
            BeanUtils.populate(serapanRealisasiEntity, request.getParameterMap());
            response.data = serapanRealisasiService.save(serapanRealisasiEntity, lokasiId);
            response.message = "Data saved successfully.";
            response.isSuccess = true;
        } catch (Exception ex) {
            response = new ResponseObject();
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject update(
            @PathVariable("id") String dataId,
            HttpServletRequest request
    ) {
        ResponseObject response = new ResponseObject();
        try {
            SerapanRealisasiEntity serapanRealisasiEntity = new SerapanRealisasiEntity();
            ConvertUtils.register((Converter) new StringToDateConverter(StringToDateConverter.DEFAULT_DATE_PATTERN), java.util.Date.class);
            BeanUtils.populate(serapanRealisasiEntity, request.getParameterMap());
            response.data = serapanRealisasiService.update(serapanRealisasiEntity, dataId);
            response.message = "Data updated successfully.";
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
        ResponseObject<SerapanRealisasiEntity> response = new ResponseObject<SerapanRealisasiEntity>();
        try {
            response.isSuccess = true;
            response.data = serapanRealisasiService.findOne(id);
        } catch (Exception ex) {
            response.message = ex.getMessage();
            response.isSuccess = false;
        }
        return response;
    }

    /**
     * Process delete request. Only accept "DELETE" ajax type.
     * Please custom your ajax request type.
     *
     * @return ResponseObject
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(
            @PathVariable("id") String id
    ) {
        ResponseObject<SerapanRealisasiEntity> response = new ResponseObject<SerapanRealisasiEntity>();
        try {
            serapanRealisasiService.remove(id);
            response.isSuccess = true;
            response.message = "Data deleted successfully.";
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
}
