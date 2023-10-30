package com.app.controller.rest.master;

import com.app.entity.LaporanMingguan;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.service.master.LaporanMingguanService;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/rest/master/master_laporan_mingguan")
public class MasterLaporanMingguanRestController {

	@Autowired
	private LaporanMingguanService vs;

	@RequestMapping(value = "/search/{lokasi_id}", method = RequestMethod.GET)
	public ResponseListObject search(
			@PathVariable("lokasi_id") String lokasiId,
			@RequestParam(value = "rows", required = false) int rows,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "search", required = false) Integer search) {
		ResponseListObject response = new ResponseListObject();
		try {
			Page<LaporanMingguan> pageData = vs.searchInt(rows, page - 1, search, lokasiId);
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
	public ResponseObject save(HttpServletRequest request,@RequestParam(value = "lokasiId", required = true) String lokasiId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
			LaporanMingguan k = new LaporanMingguan();
			DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.save(k, lokasiId);
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
	public ResponseObject update(HttpServletRequest request,@RequestParam(value = "lokasiId", required = true) String lokasiId) {
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
			LaporanMingguan k = new LaporanMingguan();
			DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, java.util.Date.class);
			BeanUtils.populate(k, request.getParameterMap());
			response.data = vs.update(k, lokasiId);
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
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
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
		ResponseObject<LaporanMingguan> response = new ResponseObject<LaporanMingguan>();
		try {
			vs.remove(id);
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
	}
        
//        @RequestMapping(value = "/ajax/{idLokasi}/{laporanKe}", method = RequestMethod.GET)
//        public DataTable ajax(@PathVariable("idLokasi") String idLokasi, @PathVariable("laporanKe") Integer laporanKe){
//            DataTable data = new DataTable();
//            
//            ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
//            cd.add(new ColumnDescription("laporanPeriodeMingguKe", ValueType.NUMBER, "Laporan Progress Minggu Ke"));
//            cd.add(new ColumnDescription("rencanaProgressMingguIni", ValueType.NUMBER, "Rencana progress Minggu Ini"));
//            cd.add(new ColumnDescription("progressMingguIni", ValueType.NUMBER, "Prograss Minggu Ini"));
//
//            data.addColumns(cd);
//            try {
//                List<LaporanMingguan> laporan = vs.findByIdLokasiNLaporanKe(idLokasi, laporanKe);
//                data.addRows(laporan);
//                    
////                data.addRows(laporan.);
//            } catch (Exception ex) {
//                
//            }
//            return data;
//        }
        
        @RequestMapping(value = "/ajax/{idLokasi}/{laporanKe}", method = RequestMethod.GET)
        public ResponseListObject ajax(@PathVariable("idLokasi") String idLokasi, @PathVariable("laporanKe") Integer laporanKe){
            ResponseListObject response = new ResponseListObject();
            try {
			List<LaporanMingguan> laporan = vs.findByIdLokasiNLaporanKe(idLokasi, laporanKe);
			response.rows = laporan;//pageData.getContent();
//			response.recordsFiltered = pageData.getTotalElements();
			response.recordsTotal = vs.count();
			response.isSuccess = true;
		} catch (Exception ex) {
			response.isSuccess = false;
			response.message = ex.getMessage();
		}
		return response;
        }
        
}
