package org.example.service;

import org.example.pojo.Category;
import org.example.pojo.PageBean;

import java.util.List;

public interface CategoryService {
    Category findCategory(String categoryName);

    void add(Category categoryName);

    void delete(Integer id);

    void update(String categoryName, Integer id);

    PageBean<Category> get(Integer pageNum, Integer pageSize);

    Category getName(Integer id);

    List<Category> getList();
}
