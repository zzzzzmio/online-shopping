package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.GoodsMapper;
import org.example.pojo.Goods;
import org.example.pojo.PageBean;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsService implements org.example.service.GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findGoods(String goodsName) {
        return goodsMapper.findGoods(goodsName);
    }

    @Override
    public void add(Goods g) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        g.setCreateUser(userId);
        goodsMapper.add(g);
    }

    @Override
    public Goods findOther(Integer id, String goodsName) {
        return goodsMapper.findOther(id,goodsName);
    }

    @Override
    public void update(Goods g) {
        goodsMapper.update(g);
    }

    @Override
    public Goods get(Integer id) {
        return goodsMapper.get(id);
    }

    @Override
    public void delete(Integer id) {
        goodsMapper.delete(id);
    }

    @Override
    public PageBean<Goods> getList(Integer pageNum, Integer pageSize, Integer categoryId) {
        PageBean<Goods> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> list=goodsMapper.getList(categoryId);
        Page<Goods> p= (Page<Goods>) list;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Goods> seller(Integer pageNum, Integer pageSize, Integer categoryId) {
        PageBean<Goods> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        List<Goods> list=goodsMapper.seller(userId,categoryId);
        Page<Goods> p= (Page<Goods>) list;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
