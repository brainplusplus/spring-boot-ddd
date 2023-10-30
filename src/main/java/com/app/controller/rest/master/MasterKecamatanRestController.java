package com.app.controller.rest.master;

import com.app.entity.Kec;
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

import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.KecamatanService;


@RestController
@RequestMapping("/rest/master/master_kecamatan")
public class MasterKecamatanRestController {

	@Autowired
	private KecamatanService vs;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseListObject search(@RequestParam(value = "rows", required = false) int rows,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "search", required = false) String search) {
		ResponseListObject response = new ResponseListObject();
		try {
			Page<Kec> pageData = vs.search(rows, page - 1, search);
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
	public ResponseObject save(HttpServletRequest request) {
		ResponseObject response = new ResponseObject();
		try {
			Kec k = new Kec();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.save(k);
			response.isSuccess = true;
		} catch (Exception ex) {
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
			Kec k = new Kec();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.update(k);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseObject findOne(@PathVariable("id") String id) {
		ResponseObject<Kec> response = new ResponseObject<Kec>();
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
		ResponseObject<Kec> response = new ResponseObject<Kec>();
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
