package com.offcn.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.pojo.PageResult;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        //pageNum代表页码值,pageSize代表每页条数
        PageHelper.startPage(pageNum,pageSize);
        //原本直接返回的是下面,但是有个实体类,这里就可以直接获取值page和pageinfo一样
       // Page<TbBrand> page= (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        // return new PageResult(page.getTotal(),page.getResult());
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandMapper.selectByExample(null));
     return new PageResult(pageInfo.getTotal(),pageInfo.getList());


    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insertSelective(tbBrand);
    }

    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKeySelective(tbBrand);
    }

    @Override
    public TbBrand findOne(long id) {

        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long l:ids ) {

            tbBrandMapper.deleteByPrimaryKey(l);
        }
    }

    @Override
    public PageResult fingPage(TbBrand tbBrand, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum,pageSize);
        TbBrandExample tb = new TbBrandExample();
        TbBrandExample.Criteria criteria = tb.createCriteria();
       if(tbBrand !=null) {
           if (tbBrand.getName() != null && tbBrand.getName().length() > 0) {

               criteria.andNameLike("%" + tbBrand.getName() + "%");

           }
           if (tbBrand.getFirstChar() != null && tbBrand.getFirstChar().length() > 0) {

               criteria.andFirstCharLike("%" + tbBrand.getFirstChar() + "%");

           }
       }

        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandMapper.selectByExample(tb));

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }
}
