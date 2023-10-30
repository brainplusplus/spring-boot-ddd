package com.app.service.master;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.PageDao;
import com.app.dao.UserDao;
import com.app.entity.PageApp;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.UploadImageObject;
import com.app.service.GenericDAOService;
import com.app.util.AssetUtil;
import com.app.util.CommonUtil;

@Service
public class PageService extends GenericDAOService<PageDao, PageApp> {

		@Autowired
		private PageDao ud;

}