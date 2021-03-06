package com.midland.web.annocontroller;

import com.midland.web.model.CenterMsg;
import com.midland.web.service.CenterMsgService;
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
@SuppressWarnings("all")
@RequestMapping("/centerMsg/")
public class CenterMsgRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(CenterMsgRestController.class);
	@Autowired
	private CenterMsgService centerMsgServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addCenterMsg(@RequestBody CenterMsg obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addCenterMsg {}",obj);
			centerMsgServiceImpl.insertCenterMsg(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addCenterMsg异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getCenterMsgById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getCenterMsgById  {}",id);
			CenterMsg centerMsg = centerMsgServiceImpl.selectCenterMsgById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(centerMsg);
		} catch(Exception e) {
			log.error("getCenterMsg异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateCenterMsgById(@RequestBody CenterMsg obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateCenterMsgById  {}",obj);
			centerMsgServiceImpl.updateCenterMsgById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCenterMsgById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findCenterMsgList(@RequestBody CenterMsg  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findCenterMsgList  {}",obj);
			MidlandHelper.doPage(request);
			Page<CenterMsg> list = (Page<CenterMsg>)centerMsgServiceImpl.findCenterMsgList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findCenterMsgList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
