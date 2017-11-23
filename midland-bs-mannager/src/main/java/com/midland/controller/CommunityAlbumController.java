package com.midland.controller;

import com.midland.web.model.CommunityAlbum;
import com.midland.web.service.CommunityAlbumService;
import com.midland.base.BaseFilter;
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
@RequestMapping("/communityAlbum/")
public class CommunityAlbumController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(CommunityAlbumController.class);
	@Autowired
	private CommunityAlbumService communityAlbumServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String communityAlbumIndex(CommunityAlbum communityAlbum,Model model) throws Exception {
		return "communityAlbum/communityAlbumIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddCommunityAlbum(CommunityAlbum communityAlbum,Model model) throws Exception {
		return "communityAlbum/addCommunityAlbum";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addCommunityAlbum(CommunityAlbum communityAlbum) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addCommunityAlbum {}",communityAlbum);
			communityAlbumServiceImpl.insertCommunityAlbum(communityAlbum);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addCommunityAlbum异常 {}",communityAlbum,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_communityAlbum")
	public String getCommunityAlbumById(Integer id,Model model) {
		log.info("getCommunityAlbumById  {}",id);
		CommunityAlbum result = communityAlbumServiceImpl.selectCommunityAlbumById(id);
		model.addAttribute("item",result);
		return "communityAlbum/updateCommunityAlbum";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteCommunityAlbumById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteCommunityAlbumById  {}",id);
			communityAlbumServiceImpl.deleteCommunityAlbumById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteCommunityAlbumById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateCommunityAlbum(Integer id,Model model) throws Exception {
		CommunityAlbum result = communityAlbumServiceImpl.selectCommunityAlbumById(id);
		model.addAttribute("item",result);
		return "communityAlbum/updateCommunityAlbum";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateCommunityAlbumById(CommunityAlbum communityAlbum) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateCommunityAlbumById  {}",communityAlbum);
			communityAlbumServiceImpl.updateCommunityAlbumById(communityAlbum);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCommunityAlbumById  {}",communityAlbum,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findCommunityAlbumList(CommunityAlbum communityAlbum,Model model, HttpServletRequest request) {
		try {
			log.info("findCommunityAlbumList  {}",communityAlbum);
			MidlandHelper.doPage(request);
			Page<CommunityAlbum> result = (Page<CommunityAlbum>)communityAlbumServiceImpl.findCommunityAlbumList(communityAlbum);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findCommunityAlbumList  {}",communityAlbum,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "communityAlbum/communityAlbumList";
	}
}