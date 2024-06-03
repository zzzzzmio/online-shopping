package org.example.controller;

import org.example.pojo.Category;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //添加商品分类
    @PostMapping("/add")
    public Result add(@RequestBody Category c){
        Category category=categoryService.findCategory(c.getCategoryName());
        //还未被创建过
        if(category==null){
            categoryService.add(c);
            return Result.success();
        }
        else {
            return Result.error("该分类已被创建");
        }
    }
    //删除商品分类
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }
    //修改商品分类
    @PutMapping("/update")
    public Result update(@RequestParam String categoryName,@RequestParam Integer id){
        Category category=categoryService.findCategory(categoryName);
        if(category==null){
            categoryService.update(categoryName,id);
            return Result.success();
        }
        return Result.error("该分类已存在");
    }
    //获取商品分类列表
    @GetMapping("get")
    public Result<PageBean<Category>> get(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageBean<Category> pb=categoryService.get(pageNum,pageSize);
        return Result.success(pb);
    }
    //获取商品分类信息
    @GetMapping("/getname")
    public Result<Category> getName(@RequestParam Integer id){
        Category category=categoryService.getName(id);
        return Result.success(category);
    }
    //获取全部商品分类列表
    @GetMapping("/getlist")
    public Result<List<Category>> getList(){
        List<Category> categories=categoryService.getList();
        return Result.success(categories);
    }
}
