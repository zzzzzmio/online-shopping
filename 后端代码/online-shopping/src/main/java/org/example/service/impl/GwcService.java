package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.GwcMapper;
import org.example.pojo.Gwc;
import org.example.pojo.PageBean;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GwcService implements org.example.service.GwcService {
    @Autowired
    private GwcMapper gwcMapper;
    @Override
    public void add(Gwc gwc) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        gwc.setCreateUser(userId);
        gwcMapper.add(gwc);
    }

    @Override
    public void update(Gwc gwc) {
        gwcMapper.update(gwc);
    }

    @Override
    public void delete(Integer id) {
        gwcMapper.delete(id);
    }

    @Override
    public PageBean<Gwc> get(Integer pageNum, Integer pageSize) {
        PageBean<Gwc> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        List<Gwc> list=gwcMapper.get(userId);
        Page<Gwc> p= (Page<Gwc>) list;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
