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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;

public class BaseController<T extends BaseDomain,E extends BaseQuery> {

    protected BaseService baseService;

    protected String listUI;
    protected String saveUI;
    protected  String modelName;
    protected  String mapping;
    protected String msg;

    protected Class<T> clazz;


    public BaseController(){



        ControllerAnno controllerAnno = this.getClass().getAnnotation(ControllerAnno.class);
        listUI = controllerAnno.listUI();
        saveUI = controllerAnno.saveUI();
        modelName = controllerAnno.modelName();
        mapping = modelName+"s";
        msg = controllerAnno.msg();

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String list(){
        return listUI;
    }

    @RequestMapping(value = "",method = RequestMethod.GET,headers ={"X-Requested-With=XMLHttpRequest"} )
    @ResponseBody
    public JsonModel list(E e){
        JsonModel jsonModel = new JsonModel();
        PageInfo<T> pageInfo = baseService.findByQuery(e);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("查找"+msg+"成功");
        jsonModel.setData(pageInfo.getList());
        jsonModel.setTotal(pageInfo.getTotal());
        return jsonModel;
    }

    @RequestMapping(value = "add",method = RequestMethod.GET )
    public String add(Model model){
        try {
            model.addAttribute(modelName,clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException  e) {
            e.printStackTrace();
        }
        return saveUI;
    }

    @RequestMapping(value = "",method = RequestMethod.POST )
    public String add(T t){
       baseService.add(t);
       return "redirect:/"+mapping;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET )
    public String findById(@PathVariable("id") int id, Model model){
       T t = (T) baseService.findById(id);

       if(t==null){
           throw new KPException(ExceptionKind.PARAM_E,"编辑"+msg+"["+id+"]参数异常");
       }
        model.addAttribute(modelName,t);
        return saveUI;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public String edit(T t){
        boolean sign = baseService.update(t);
        if(sign){
            return "redirect:/"+mapping;
        }else{
            throw new KPException(ExceptionKind.BUSINESS_E,"编辑"+msg+"["+t.getId()+"]参数异常");
        }
    }


    @RequestMapping(value = "{ids}",method = RequestMethod.DELETE,headers ={"X-Requested-With=XMLHttpRequest"})
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
}
