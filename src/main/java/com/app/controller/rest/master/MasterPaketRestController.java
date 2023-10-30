package com.app.controller.rest.master;

import com.app.entity.Paket;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.PaketService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/master/master_paket")
public class MasterPaketRestController {

    @Autowired
    private PaketService paketService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseListObject search(
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "tahun", required = false) String tahun
    ) {
        ResponseListObject response = new ResponseListObject();
        try {
            Page<Paket> paketData = paketService.searchPaket(rows, page - 1, search, tahun);
            response.rows = paketData.getContent();
            response.recordsTotal = paketService.count();
            response.recordsFiltered = paketData.getTotalElements();
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
            Paket paketEntity = new Paket();
            BeanUtils.populate(paketEntity, request.getParameterMap());
            response.isSuccess = true;
            response.data = paketService.save(paketEntity);
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
            Paket paketEntity = new Paket();
            BeanUtils.populate(paketEntity, request.getParameterMap());
            response.message = "Data updated successfully.";
            response.data = paketService.update(paketEntity);
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
        ResponseObject<Paket> response = new ResponseObject<Paket>();
        try {
            response.isSuccess = true;
            response.data = paketService.findOne(id);
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }

    /**
     * Process delete request. Only accept "DELETE" ajax type.
     * Please custom your ajax request type.
     *
     * @param String id
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject remove(
            @PathVariable("id") String id
    ) {
        ResponseObject<Paket> response = new ResponseObject<Paket>();
        try {
            paketService.remove(id);
            response.message = "Data deleted successfully.";
            response.isSuccess = true;
        } catch (Exception ex) {
            response.isSuccess = false;
            response.message = ex.getMessage();
        }
        return response;
    }
}
