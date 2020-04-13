package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.pojo.PageResult;
import com.offcn.pojo.Result;
import com.offcn.pojo.TbBrand;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<TbBrand> findAll() {
        return brandService.findAll();

    }

    @RequestMapping("findPage")
    @ResponseBody
    public PageResult findPage(int page, int rows) {
        return brandService.findPage(page, rows);
    }



    @RequestMapping("add")
    @ResponseBody
   public Result add(@RequestBody TbBrand tbBrand){

        try {
            brandService.add(tbBrand);
            return new Result(true,"增加成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"增加失败");

        }

   }

   @RequestMapping("update")
    @ResponseBody
    public Result updata(@RequestBody TbBrand tbBrand){

       try {
           brandService.update(tbBrand);
           return new Result(true,"修改成功");
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false,"修改失败");
       }
   }

   @RequestMapping("findOne")
    @ResponseBody
    public TbBrand findOne(Long id){

       return brandService.findOne(id);
   }
@RequestMapping("delete")
    @ResponseBody
    public Result delete(Long[] ids){


    try {
        brandService.delete(ids);
        return new Result(true,"删除成功");
    } catch (Exception e) {
        e.printStackTrace();
        return  new Result(false,"删除失败");
    }


}

@RequestMapping("search")
    @ResponseBody
    public PageResult search(@RequestBody TbBrand tbBrand,int page,int rows){

       return brandService.fingPage(tbBrand,page,rows) ;

}
@RequestMapping("selectOptionList")
    @ResponseBody
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
}
}
