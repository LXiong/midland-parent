package com.midland.web.dao;

import com.midland.web.model.Footer;
import com.midland.web.model.HotSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotSearchMapper {

	HotSearch selectHotSearchById(Integer hotSearch);

	int deleteHotSearchById(Integer hotSearch);

	int updateHotSearchById(HotSearch hotSearch);

	int insertHotSearch(HotSearch hotSearch);

	List<HotSearch> findHotSearchList(HotSearch hotSearch);

	int batchUpdate(@Param("hotSearchList") List<HotSearch> hotSearchList);

}
