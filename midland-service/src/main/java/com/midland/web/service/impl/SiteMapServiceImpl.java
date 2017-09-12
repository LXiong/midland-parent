package com.midland.web.service.impl;

import com.midland.web.model.SiteMap;
import com.midland.web.dao.SiteMapMapper;
import com.midland.web.service.SiteMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SiteMapServiceImpl implements SiteMapService {

	private Logger log = LoggerFactory.getLogger(SiteMapServiceImpl.class);
	@Autowired
	private SiteMapMapper siteMapMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertSiteMap(SiteMap siteMap) throws Exception {
		try {
			log.info("insert {}",siteMap);
			siteMapMapper.insertSiteMap(siteMap);
		} catch(Exception e) {
			log.error("insertSiteMap异常 {}",siteMap,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public SiteMap selectSiteMapById(Integer id) {
		log.info("selectSiteMapById  {}",id);
		return siteMapMapper.selectSiteMapById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteSiteMapById(Integer id)throws Exception {
		try {
			log.info("deleteSiteMapById  {}",id);
			int result = siteMapMapper.deleteSiteMapById(id);
			if (result < 1) {
				throw new Exception("deleteSiteMapById失败");
			}
		} catch(Exception e) {
			log.error("deleteSiteMapById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateSiteMapById(SiteMap siteMap) throws Exception {
		try {
			log.info("updateSiteMapById  {}",siteMap);
			int result = siteMapMapper.updateSiteMapById(siteMap);
			if (result < 1) {
				throw new Exception("updateSiteMapById失败");
			}
		} catch(Exception e) {
			log.error("updateSiteMapById  {}",siteMap,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<SiteMap> findSiteMapList(SiteMap siteMap) throws Exception {
		try {
			log.info("findSiteMapList  {}",siteMap);
			return siteMapMapper.findSiteMapList(siteMap);
		} catch(Exception e) {
			log.error("findSiteMapList  {}",siteMap,e);
			throw e;
		}
	}
}