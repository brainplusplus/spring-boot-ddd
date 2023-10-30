package com.app.service.master;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.PageDao;
import com.app.dao.RoleDao;
import com.app.dao.RolePageDao;
import com.app.dao.UserDao;
import com.app.entity.PageApp;
import com.app.entity.Role;
import com.app.entity.RolePage;
import com.app.object.ResponseListObject;
import com.app.object.ResponseObject;
import com.app.object.UploadImageObject;
import com.app.service.GenericDAOService;
import com.app.util.AssetUtil;
import com.app.util.CommonUtil;

@Service
public class RolePageService extends GenericDAOService<RolePageDao, RolePage> {

		@Autowired
		private RolePageDao rpd;
		
		@Autowired
		private RoleDao rd;
		
		@Autowired
		private PageDao pd;
		
		public Page<RolePage> searchWithRoleId(int rows, int page, String search,String roleId) {
			PageRequest paginationRequest = new PageRequest(page, rows);
			Page<RolePage> hasil = null; 	
			if(CommonUtil.isEmpty(search) && CommonUtil.isEmpty(roleId)){
				hasil = rpd.findAll(paginationRequest);
			}else if(!CommonUtil.isEmpty(search) && CommonUtil.isEmpty(roleId)){
				hasil = rpd.searchPageByAllColumnLike("%" + search + "%", paginationRequest);
			}else if(!CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(roleId)){
				hasil = rpd.searchPageByAllColumnLikeAndRoleId("%" + search + "%", roleId, paginationRequest);
			}
			else if(CommonUtil.isEmpty(search) && !CommonUtil.isEmpty(roleId)){
				hasil = rpd.searchPageByRoleId(roleId, paginationRequest);
			}
			return hasil;
		}
		@Transactional(readOnly = false , rollbackFor = Exception.class)
		public void saveUpdate(String roleId,List<String> listPage) throws Exception
		{
				Role role = rd.findOne(roleId);
				if(role==null){
					throw new Exception("Not found ROLE");
				}
				List<RolePage> rolePagesOld = rpd.searchAllByRoleId(roleId);
				for(RolePage rpOld:rolePagesOld){
					rpd.delete(rpOld);
				}
				for(String pageId:listPage){
					PageApp page = pd.findOne(pageId);
					if(page==null){
						System.out.println("Page with ID:"+pageId+" is skipped");
					}else{
						RolePage rolePage = new RolePage();
						rolePage.setId(UUID.randomUUID().toString());
						rolePage.setIdPage(page);
						rolePage.setIdRole(role);
						rolePage.setDateCreated(new Date());
						rolePage.setDateUpdate(new Date());
						rpd.save(rolePage);
					}
				}
		}
}