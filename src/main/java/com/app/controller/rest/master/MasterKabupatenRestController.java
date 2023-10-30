package com.app.controller.rest.master;

import com.app.entity.Kab;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.KabupatenService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by driyanh on 06/11/2015.
 */
@RestController
@RequestMapping("/rest/master/master_kabupaten")
public class MasterKabupatenRestController {

	@Autowired
	private KabupatenService vs;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseListObject search(@RequestParam(value = "rows", required = false) int rows,
									 @RequestParam(value = "page", required = false) int page,
									 @RequestParam(value = "search", required = false) String search) {
		ResponseListObject response = new ResponseListObject();
		try {
			Page<Kab> pageData = vs.search(rows, page - 1, search);
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
		ResponseObject<Kab> response = new ResponseObject<Kab>();
		try {
			Kab k = new Kab();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.save(k);
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
	public ResponseObject update(HttpServletRequest request) {
		ResponseObject<Kab> response = new ResponseObject<Kab>();
		try {
			Kab k = new Kab();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.update(k);
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
		ResponseObject<Kab> response = new ResponseObject<Kab>();
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
		ResponseObject<Kab> response = new ResponseObject<Kab>();
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
