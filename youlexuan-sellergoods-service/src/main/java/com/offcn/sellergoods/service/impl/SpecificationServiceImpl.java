package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.group.Specification;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.pojo.*;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.pojo.TbSpecificationExample.Criteria;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

    public void setSpecificationOptionMapper(TbSpecificationOptionMapper specificationOptionMapper) {
        this.specificationOptionMapper = specificationOptionMapper;
    }

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;
    /**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		specificationMapper.insertSelective(specification.getSpecification());
        for (TbSpecificationOption sp: specification.getSpecificationOptionList()) {
            sp.setSpecId(specification.getSpecification().getId());
            System.out.println(sp);
            specificationOptionMapper.insertSelective(sp);
        }


	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
	    //保存修改规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());
		/*/删除 /*/
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
        //增加
        for (TbSpecificationOption sp: specification.getSpecificationOptionList()) {
            sp.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insertSelective(sp);


        }


	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
	    Specification specification = new Specification();
		//查询规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);

       specification.setSpecification(tbSpecification);
       specification.setSpecificationOptionList(tbSpecificationOptions);

        return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//删除规格并且删除与他关联的规格项
		    specificationMapper.deleteByPrimaryKey(id);
		    TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
            criteria.andSpecIdEqualTo(id);
           specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
        }		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
