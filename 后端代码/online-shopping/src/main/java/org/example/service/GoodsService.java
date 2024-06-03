package org.example.service;

import org.example.pojo.Goods;
import org.example.pojo.PageBean;

public interface GoodsService {
    Goods findGoods(String goodsName);

    void add(Goods g);

    Goods findOther(Integer id, String goodsName);

    void update(Goods g);

    Goods get(Integer id);

    void delete(Integer id);

    PageBean<Goods> getList(Integer pageNum, Integer pageSize, Integer categoryId);

    PageBean<Goods> seller(Integer pageNum, Integer pageSize, Integer categoryId);
}
