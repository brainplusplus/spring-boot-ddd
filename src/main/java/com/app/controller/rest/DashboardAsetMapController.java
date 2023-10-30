package com.app.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.ProvinsiService;

//import scala.util.parsing.json.JSONObject;

@RestController
@RequestMapping("/rest/dashboard")
public class DashboardAsetMapController {
	
	@Autowired
	private ProvinsiService pro;

}
