package com.midland.web.controller;

import com.midland.web.model.SiteMap;
import com.midland.web.service.SiteMapService;
import com.midland.web.controller.base.BaseController;
import org.slf4j.Logger;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/siteMap/")
public class SiteMapController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(SiteMapController.class);
	@Autowired
	private SiteMapService siteMapServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String siteMapIndex(SiteMap siteMap,Model model) throws Exception {
		return "siteMap/siteMapIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddSiteMap(SiteMap siteMap,Model model) throws Exception {
		return "siteMap/addSiteMap";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addSiteMap(SiteMap siteMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addSiteMap {}",siteMap);
			siteMapServiceImpl.insertSiteMap(siteMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addSiteMap异常 {}",siteMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_siteMap")
	public String getSiteMapById(Integer id,Model model) {
		log.info("getSiteMapById  {}",id);
		SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
		model.addAttribute("item",result);
		return "siteMap/updateSiteMap";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteSiteMapById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteSiteMapById  {}",id);
			siteMapServiceImpl.deleteSiteMapById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteSiteMapById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateSiteMap(Integer id,Model model) throws Exception {
		SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
		model.addAttribute("item",result);
		return "siteMap/updateSiteMap";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateSiteMapById(SiteMap siteMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateSiteMapById  {}",siteMap);
			siteMapServiceImpl.updateSiteMapById(siteMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateSiteMapById  {}",siteMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findSiteMapList(SiteMap siteMap,Model model, HttpServletRequest request) {
		try {
			log.info("findSiteMapList  {}",siteMap);
			MidlandHelper.doPage(request);
			Page<SiteMap> result = (Page<SiteMap>)siteMapServiceImpl.findSiteMapList(siteMap);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findSiteMapList  {}",siteMap,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "siteMap/siteMapList";
	}
}