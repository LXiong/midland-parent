package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.commons.core.util.DateUtils;
import com.midland.web.model.RecruitManager;
import com.midland.web.service.RecruitManagerService;
import com.midland.base.BaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
/**
 * 招聘
 */
@SuppressWarnings("all")
@RequestMapping("/recruitManager/")
public class RecruitManagerRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RecruitManagerRestController.class);
	@Autowired
	private RecruitManagerService recruitManagerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addRecruitManager(@RequestBody RecruitManager obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addRecruitManager {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			recruitManagerServiceImpl.insertRecruitManager(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addRecruitManager异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getRecruitManagerById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getRecruitManagerById  {}",id);
			RecruitManager recruitManager = recruitManagerServiceImpl.selectRecruitManagerById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(recruitManager);
		} catch(Exception e) {
			log.error("getRecruitManager异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateRecruitManagerById(@RequestBody RecruitManager obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateRecruitManagerById  {}",obj);
			recruitManagerServiceImpl.updateRecruitManagerById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateRecruitManagerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findRecruitManagerList(@RequestBody RecruitManager  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findRecruitManagerList  {}",obj);
			obj.setOnlineTime(MidlandHelper.getCurrentTime());
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<RecruitManager> list = (Page<RecruitManager>)recruitManagerServiceImpl.findRecruitManagerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findRecruitManagerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
