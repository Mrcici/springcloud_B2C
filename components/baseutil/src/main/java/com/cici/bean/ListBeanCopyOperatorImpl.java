package com.cici.bean;

import com.google.common.collect.Lists;
import com.cici.tools.BeanUtils;

import java.lang.reflect.Field;
import java.util.List;

public class ListBeanCopyOperatorImpl implements BeanCopyOperator<ListBean>{

    private ListBean listBean;

    public ListBeanCopyOperatorImpl(ListBean listBean) {
        this.listBean = listBean;
    }

    @Override
    public void copy(Field source, Field target, Object sourceObj, Object targetObj, BeanCopyOperator ...operators){

        try{
            List<Object> result = Lists.newArrayList();
            source.setAccessible(true);
            Object srcValue = source.get(sourceObj);
            List<Object> list = (List)srcValue;
            if (srcValue != null) {
                for (Object o : list) {
                    try {
                        Object d = listBean.getTargetClass().newInstance();
                        BeanUtils.copyex(o, d, operators);
                        result.add(d);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            target.setAccessible(true);
            target.set(targetObj, result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean isSupport(String field) {
        return listBean.getOptField().equals(field);
    }
}
