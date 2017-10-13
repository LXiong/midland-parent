package com.midland.web.dao;

import com.midland.web.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryMapper {

	Category selectCategoryById(Integer category);

	int deleteCategoryById(Integer category);

	int updateCategoryById(Category category);

	int insertCategory(Category category);

	List<Category> findCategoryList(Category category);

	List<Category> findCategoryTreeList(Category category);

	List<Category> findCategoryParentNameList(Category category);

	Category selectCategoryParentById(Integer category);

}
