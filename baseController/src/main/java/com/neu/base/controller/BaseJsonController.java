package com.neu.base.controller;

import com.github.pagehelper.PageInfo;
import com.neu.base.anno.ControllerAnno;
import com.neu.common.domain.BaseDomain;
import com.neu.common.exception.ExceptionKind;
import com.neu.common.exception.KPException;
import com.neu.common.utils.JsonModel;
import com.neu.query.BaseQuery;
import com.neu.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

public class BaseJsonController<T extends BaseDomain,E extends BaseQuery> {

    protected BaseService baseService;
 
    protected  String modelName;
    protected  String mapping;
    protected String msg;

    protected Class<T> clazz;


    public BaseJsonController(){

        ControllerAnno controllerAnno = this.getClass().getAnnotation(ControllerAnno.class);
       
        modelName = controllerAnno.modelName();
        mapping = modelName+"s";
        msg = controllerAnno.msg();

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

   

    @RequestMapping(value = "/query",method = RequestMethod.POST )
    @ResponseBody
    public JsonModel list(@RequestBody E e){
        JsonModel jsonModel = new JsonModel();
        PageInfo<T> pageInfo = baseService.findByQuery(e);
        System.out.println(pageInfo);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("查找"+msg+"成功");
        jsonModel.setData(pageInfo.getList());
        jsonModel.setTotal(pageInfo.getTotal());
        return jsonModel;
    }


    @RequestMapping(value = "",method = RequestMethod.POST )
    @ResponseBody
    public JsonModel add(@RequestBody T t){
        JsonModel jsonModel = new JsonModel();
        baseService.add(t);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加"+msg+"成功");
        return jsonModel;
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JsonModel save(@RequestBody T t){
        JsonModel jsonModel = new JsonModel();
        boolean sign = baseService.save(t);
        if(sign){
             jsonModel.setSuccess(true);
             jsonModel.setMsg("保存"+msg+"成功");
        }else{
            jsonModel.setSuccess(false);
            jsonModel.setMsg("保存"+msg+"失败");
        }
        return jsonModel;
    }


    @RequestMapping(value = "{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonModel deleteByIds(@PathVariable("ids") String ids){
        JsonModel jsonModel = new JsonModel();
        String [] id =  ids.split(",");
        int  [] intId = new int[id.length];
        int length = id.length;
        for(int i=0;i<length;i++){
            intId[i]=Integer.parseInt(id[i]);
        }
        baseService.deleteByIds(intId);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("成功删除"+length+"个"+msg);
        return jsonModel;
    }

/*    @RequestMapping(value="products/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JsonModel findById(@PathVariable("id") Integer productId);*/

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public T findById(@PathVariable("id") int id){
        T t=  (T) baseService.findById(id);
        if(t==null){
            throw new KPException(ExceptionKind.PARAM_E,"编辑"+msg+"["+id+"]参数异常");

        }else{
            System.out.println(t.getId());
            return t;
        }
    }


    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonModel edit(@RequestBody T t){
        JsonModel jsonModel = new JsonModel();
        boolean sign = baseService.update(t);
        if(sign){
            jsonModel.setSuccess(true);
            jsonModel.setMsg("编辑"+msg+"成功");
        }else{
            jsonModel.setSuccess(false);
            jsonModel.setMsg("编辑"+msg+"失败");
        }
        return jsonModel;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public boolean update(@RequestBody T t){
        return baseService.update(t);
    }





}
