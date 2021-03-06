package com.midland.web.service.impl;

import com.midland.web.model.Category;
import com.midland.web.dao.CategoryMapper;
import com.midland.web.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertCategory(Category category) throws Exception {
		try {
			log.debug("insert {}",category);
			categoryMapper.insertCategory(category);
		} catch(Exception e) {
			log.error("insertCategory异常 {}",category,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Category selectCategoryById(Integer id) {
		log.debug("selectCategoryById  {}",id);
		return categoryMapper.selectCategoryById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteCategoryById(Integer id)throws Exception {
		try {
			log.debug("deleteCategoryById  {}",id);
			int result = categoryMapper.deleteCategoryById(id);
			if (result < 1) {
				throw new Exception("deleteCategoryById失败");
			}
		} catch(Exception e) {
			log.error("deleteCategoryById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateCategoryById(Category category) throws Exception {
		try {
			log.debug("updateCategoryById  {}",category);
			int result = categoryMapper.updateCategoryById(category);
			if (result < 1) {
				throw new Exception("updateCategoryById失败");
			}
		} catch(Exception e) {
			log.error("updateCategoryById  {}",category,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Category> findCategoryList(Category category) throws Exception {
		try {
			log.debug("findCategoryList  {}",category);
			return categoryMapper.findCategoryParentNameList(category);
		} catch(Exception e) {
			log.error("findCategoryList  {}",category,e);
			throw e;
		}
	}

    @Override
    public List<Category> findCategoryTreeList(Category category) throws Exception {
		try {
			return categoryMapper.findCategoryTreeList(category);
		} catch(Exception e) {
			log.error("findCategoryTreeList  {}",category,e);
			throw e;
		}
    }

    @Override
    public Category selectCategoryParentNameById(Integer id) {
		try {
			return categoryMapper.selectCategoryParentById(id);
		}catch (Exception e){
			log.error("selectCategoryParentNameById  {}",id,e);
			throw e;
		}
    }

	@Override
	public void batchUpdate(List<Category> categoryList) throws Exception {
		try {
			log.debug("updateCategoryById  {}",categoryList);
			int result = categoryMapper.batchUpdate(categoryList);
			if (result < 1) {
				throw new Exception("updateAppointLogById失败");
			}
		} catch(Exception e) {
			log.error("updateCategoryById  {}",categoryList,e);
			throw e;
		}
	}

	@Override
	public List<Category> findleveCategory(Category category) throws Exception {
		try {
			List<Category> catetList = this.findCategoryList(category);
			return this.testMune(catetList);
		}catch (Exception e){
			log.error("updateCategoryById  {}",category,e);
			throw e;
		}

	}


	public List<Category> testMune(List<Category> categoryList) {

		// 最后的结果
		List<Category> menuList = new ArrayList<Category>();
		// 先找到所有的一级菜单
		for (int i = 0; i < categoryList.size(); i++) {
			// 一级菜单没有parentId
			if (categoryList.get(i).getParentId() == 0) {
				menuList.add(categoryList.get(i));
			}
		}
		// 为一级菜单设置子菜单，getChild是递归调用的
		for (Category menu : menuList) {
			menu.setParents(getChild(menu.getId(), categoryList));
		}

		return menuList;

	}

	/**
	 * 递归查找子菜单
	 *
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private static List<Category> getChild(Integer id, List<Category> rootMenu) {
		// 子菜单
		List<Category> childList = new ArrayList<>();
		for (Category menu : rootMenu) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getParentId() != 0) {
				if (menu.getParentId().equals(id)) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (Category menu : childList) {// 没有url子菜单还有子菜单
			/* if (StringUtils.isEmpty(menu.getUrl())) { */
			// 递归
			menu.setParents(getChild(menu.getId(), rootMenu));
			/* } */
		} // 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}



}
