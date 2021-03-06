package com.midland.web.service;

import com.midland.web.model.CommunityAlbum;
import java.util.List;
public interface CommunityAlbumService {

	int getMaxOrderBy(Integer hotHandId);

	/**
	 * 主键查询
	 **/
	CommunityAlbum selectCommunityAlbumById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteCommunityAlbumById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateCommunityAlbumById(CommunityAlbum communityAlbum) throws Exception;

	/**
	 * 插入
	 **/
	void insertCommunityAlbum(CommunityAlbum communityAlbum) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<CommunityAlbum> findCommunityAlbumList(CommunityAlbum communityAlbum) throws Exception;

}
