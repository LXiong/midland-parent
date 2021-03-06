package com.midland.web.annocontroller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.CommunityAlbum;
import com.midland.web.model.HotHand;
import com.midland.web.model.LayoutMap;
import com.midland.web.service.CommunityAlbumService;
import com.midland.web.service.HotHandService;
import com.midland.web.service.LayoutMapService;
import com.midland.web.service.impl.PublicService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@SuppressWarnings("all")
@RequestMapping("/hotHand/")
public class HotHandRestController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(HotHandRestController.class);
    @Autowired
    private HotHandService hotHandServiceImpl;
    @Autowired
    private CommunityAlbumService communityAlbumServiceImpl;
    @Autowired
    private LayoutMapService lyoutMapServiceImpl;
    @Autowired
    private PublicService publicServiceImpl;

    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addHotHand(@RequestBody HotHand obj) throws Exception {
        Result result = new Result();
        try {
            log.info("addHotHand {}", obj);
            hotHandServiceImpl.insertHotHand(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("addHotHand异常 {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getHotHandById(Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer) map.get("id");
            log.info("getHotHandById  {}", id);
            HotHand hotHand = hotHandServiceImpl.selectHotHandById(id);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(hotHand);
        } catch (Exception e) {
            log.error("getHotHand异常 {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get/info")
    public Object getHotHandAndLayoutMapById(@RequestBody Map map) {
        Map result = new HashMap();
        try {
            Integer id = (Integer) map.get("id");
            log.info("getHotHandById  {}", id);
            HotHand hotHand = hotHandServiceImpl.selectHotHandById(id);
            if (StringUtils.isNotEmpty(hotHand.getImgUrl())){
                String[] array = hotHand.getImgUrl().split("\\|\\|");
                List<String> imgList = Arrays.asList(array);
                hotHand.setImgUrlList(imgList);
            }else{
                hotHand.setImgUrlList(Collections.EMPTY_LIST);
            }
            CommunityAlbum communityAlbum = new CommunityAlbum();
            communityAlbum.setHotHandId(hotHand.getId());
            List<CommunityAlbum> communityAlbumResult = communityAlbumServiceImpl.findCommunityAlbumList(communityAlbum);
            List<Map> a = new ArrayList<>();
            List<Map> b = new ArrayList<>();
            for (CommunityAlbum temp : communityAlbumResult) {
                if (temp.getType() == 0) {
                    //实景
                    if (StringUtils.isNotEmpty(temp.getImgUrl())||StringUtils.isNotEmpty(temp.getDescription())) {
                        Map mapTemp = new HashMap();
                        mapTemp.put("imgUrl", temp.getImgUrl());
                        mapTemp.put("description", temp.getDescription());
                        a.add(mapTemp);
                    }
                } else if (temp.getType() == 1) {
                    //户型
                    if (StringUtils.isNotEmpty(temp.getImgUrl())||StringUtils.isNotEmpty(temp.getDescription())) {
                        Map mapTemp = new HashMap();
                        mapTemp.put("imgUrl", temp.getImgUrl());
                        mapTemp.put("description", temp.getDescription());
                        b.add(mapTemp);
                    }
                }
            }
            LayoutMap layoutMap = new LayoutMap();
            layoutMap.setHotHandId(hotHand.getId());
            List<LayoutMap> layoutMapResult = lyoutMapServiceImpl.findLayoutMapList(layoutMap);
            List<Map> aa = new ArrayList<>();
            List<Map> bb = new ArrayList<>();
            List<Map> cc = new ArrayList<>();
            for (LayoutMap temp : layoutMapResult) {
                if ("0".equals(temp.getType())) {
                    //一室
                    Map mapTemp = new HashMap();
                    mapTemp.put("imgUrl", temp.getImgUrl());
                    mapTemp.put("title", temp.getTitle());
                    mapTemp.put("turned", temp.getTurned());
                    mapTemp.put("acreage", temp.getAcreage());
                    mapTemp.put("avgPrice", temp.getAvgPrice());
                    mapTemp.put("saleingNum", temp.getSaleingNum());
                    mapTemp.put("price", temp.getPrice());
                    aa.add(mapTemp);

                } else if ("1".equals(temp.getType())) {
                    //二室
                    Map mapTemp = new HashMap();
                    mapTemp.put("imgUrl", temp.getImgUrl());
                    mapTemp.put("title", temp.getTitle());
                    mapTemp.put("turned", temp.getTurned());
                    mapTemp.put("acreage", temp.getAcreage());
                    mapTemp.put("avgPrice", temp.getAvgPrice());
                    mapTemp.put("saleingNum", temp.getSaleingNum());
                    mapTemp.put("price", temp.getPrice());
                    bb.add(mapTemp);
                } else if ("2".equals(temp.getType())) {
                    //三室
                    Map mapTemp = new HashMap();
                    mapTemp.put("imgUrl", temp.getImgUrl());
                    mapTemp.put("title", temp.getTitle());
                    mapTemp.put("turned", temp.getTurned());
                    mapTemp.put("acreage", temp.getAcreage());
                    mapTemp.put("avgPrice", temp.getAvgPrice());
                    mapTemp.put("saleingNum", temp.getSaleingNum());
                    mapTemp.put("price", temp.getPrice());
                    cc.add(mapTemp);
                }
            }
            Map mapResult = new HashMap();
            mapResult.put("hotHand", hotHand);
            mapResult.put("outdoorScene", a);
            mapResult.put("layScene", b);
            mapResult.put("oneRoom", aa);
            mapResult.put("twoRoom", bb);
            mapResult.put("threeRoom", cc);
            result.put("code", ResultStatusUtils.STATUS_CODE_200);
            result.put("msg", "success");
            result.put("model", mapResult);
        } catch (Exception e) {
            log.error("getHotHand异常 {}", map, e);
            result.put("code", ResultStatusUtils.STATUS_CODE_203);
            result.put("msg", "service error");
        }
        return JSONArray.toJSONString(result);
    }
    /**
     * 更新
     **/
    @RequestMapping("update")
    public Object updateHotHandById(@RequestBody HotHand obj) throws Exception {
        Result result = new Result();
        try {
            log.info("updateHotHandById  {}", obj);
            hotHandServiceImpl.updateHotHandById(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("updateHotHandById  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findHotHandList(@RequestBody HotHand obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            log.info("findHotHandList  {}", obj);
            MidlandHelper.doPage(request);
            Page<HotHand> list = (Page<HotHand>) hotHandServiceImpl.findHotHandList(obj);
            Paginator paginator = list.getPaginator();
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(list);
            result.setPaginator(paginator);
        } catch (Exception e) {
            log.error("findHotHandList  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }
}
