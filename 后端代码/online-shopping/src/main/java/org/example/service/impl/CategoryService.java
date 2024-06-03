package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.pojo.PageBean;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements org.example.service.CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category findCategory(String categoryName) {
        return categoryMapper.findCategory(categoryName);
    }

    @Override
    public void add(Category c) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        c.setCreateUser(userId);
        categoryMapper.add(c);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    @Override
    public void update(String categoryName, Integer id) {
        categoryMapper.update(categoryName,id);
    }

    @Override
    public PageBean<Category> get(Integer pageNum, Integer pageSize) {
        PageBean<Category> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list=categoryMapper.get();
        Page<Category> p= (Page<Category>) list;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Category getName(Integer id) {
        return categoryMapper.getName(id);
    }

    @Override
    public List<Category> getList() {
        return categoryMapper.getList();
    }
}
