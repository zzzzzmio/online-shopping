package org.example.service;

import org.example.pojo.Gwc;
import org.example.pojo.PageBean;

public interface GwcService {
    void add(Gwc gwc);

    void update(Gwc gwc);

    void delete(Integer id);

    PageBean<Gwc> get(Integer pageNum, Integer pageSize);
}
