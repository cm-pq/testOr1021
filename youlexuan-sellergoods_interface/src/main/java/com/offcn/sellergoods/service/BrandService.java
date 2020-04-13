package com.offcn.sellergoods.service;

import com.offcn.pojo.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public List<TbBrand> findAll();

    public PageResult findPage(int pageNum, int pageSize);

    public void add(TbBrand tbBrand);

    public void update(TbBrand tbBrand);

    //根据id获取实体
    public TbBrand findOne(long id);
//删除
    public void delete(Long[] ids);
    //条件搜索
    public PageResult fingPage(TbBrand tbBrand,int pageNum,int pageSize);
//品牌下拉选择框
    public List<Map> selectOptionList();


}
