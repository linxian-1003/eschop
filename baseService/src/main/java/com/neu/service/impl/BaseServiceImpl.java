package com.neu.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.common.domain.BaseDomain;
import com.neu.common.exception.ExceptionKind;
import com.neu.common.exception.KPException;
import com.neu.mapper.BaseMapper;
import com.neu.query.BaseQuery;
import com.neu.service.BaseService;
import java.util.List;

public class BaseServiceImpl<T extends BaseDomain,E extends BaseQuery> implements BaseService<T, E> {


    //这里需要一个mapper对象,并且mapper对象由子类来设置

    protected BaseMapper<T,E> baseMapper;


    @Override
    public T findById(int id) {
        return baseMapper.findById(id);
    }

    @Override
    public PageInfo<T> findByQuery(E e) {
        PageInfo<T> pageInfo=null;
        if(e.isPaging()){
            PageHelper.startPage(e.getPageNum(),e.getPageSize());
            Page<T> page = (Page<T>) baseMapper.findByQuery(e);
            pageInfo = page.toPageInfo();
        }else{
            List<T> data = baseMapper.findByQuery(e);
            pageInfo = new PageInfo<>();
            pageInfo.setList(data);
        }
        return pageInfo;
    }

    @Override
    public boolean add(T t) {
        return baseMapper.add(t)==1;
    }

    @Override
    public boolean deleteById(int id) {
        return baseMapper.deleteById(id)==1;
    }

    @Override
    public boolean deleteByIds(int[] ids) {
        int realCount = baseMapper.deleteByIds(ids);
        if(realCount!=ids.length){
            throw new KPException(ExceptionKind.PARAM_E);
        }
        return true;
    }

    @Override
    public boolean update(T t) {
        return baseMapper.update(t)==1;
    }

    @Override
    public boolean save(T t) {
        if(t.getId()==null){
            return baseMapper.add(t)==1;
        }else{
            return baseMapper.update(t)==1;
        }
    }
}