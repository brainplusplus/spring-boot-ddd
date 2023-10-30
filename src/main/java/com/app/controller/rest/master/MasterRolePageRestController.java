package com.app.controller.rest.master;

import com.app.entity.RolePage;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.RolePageService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by driyanh on 06/11/2015.
 */
@RestController
@RequestMapping("/rest/master/master_role_page")
public class MasterRolePageRestController {

	@Autowired
	private RolePageService us;

	@RequestMapping(value = "/search/{role_id}", method = RequestMethod.GET)
	public ResponseListObject search(
			@PathVariable("role_id") String roleId,
			@RequestParam(value = "rows", required = false) int rows,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "search", required = false) String search) {
		ResponseListObject response = new ResponseListObject();
		try {
			Page<RolePage> pageData = us.searchWithRoleId(rows, page - 1, search, roleId);
			response.rows = pageData.getContent();
			response.recordsFiltered = pageData.getTotalElements();
			response.recordsTotal = us.count();
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
			@RequestParam(value = "role_id", required = true) String roleId) {
		ResponseObject response = new ResponseObject();
		try {
			RolePage k = new RolePage();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = us.save(k);
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
			@RequestParam(value = "role_id", required = true) String roleId) {
		ResponseObject response = new ResponseObject();
		try {
			RolePage k = new RolePage();
			BeanUtils.populate(k, request.getParameterMap());
			response.data = us.update(k);
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
		ResponseObject<RolePage> response = new ResponseObject<RolePage>();
		try {
			response.data = us.findOne(id);
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
		ResponseObject<RolePage> response = new ResponseObject<RolePage>();
		try {
			us.remove(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
	
	@RequestMapping(value = "/get_by_role/{role_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseObject findAllByRoleId(@PathVariable("role_id") String roleId) {
		ResponseObject response = new ResponseObject();
		try {
			Page<RolePage> res = us.searchWithRoleId(Integer.MAX_VALUE, 0, null, roleId);
			Map<String,String> map = new HashMap<String,String>();
			for(RolePage rolePage:res.getContent()){
				map.put(rolePage.getIdPage().getId(), "1");
			}
			response.data = map;
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
	
	@RequestMapping(value = "/save_update/{role_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseObject saveUpdate(HttpServletRequest request,
			@PathVariable("role_id") String roleId) {
		ResponseObject response = new ResponseObject();
		try {
			List<String> listPage = new ArrayList<String>(request.getParameterMap().keySet());
			us.saveUpdate(roleId, listPage);
			response.isSuccess = true;
		} catch (Exception ex) {
			response = new ResponseObject();
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}

}