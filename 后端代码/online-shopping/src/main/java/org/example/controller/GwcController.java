package org.example.controller;

import org.example.pojo.Gwc;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.GwcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/gwc")
public class GwcController {
    @Autowired
    private GwcService gwcService;

    //添加购物车
    @PostMapping("/add")
    public Result add(@RequestBody Gwc gwc){
        gwcService.add(gwc);
        return Result.success();
    }
    //修改购物车
    @PutMapping("/update")
    public Result update(@RequestBody Gwc gwc){
        gwcService.update(gwc);
        return Result.success();
    }
    //删除购物车
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        gwcService.delete(id);
        return Result.success();
    }
    //获取购物车列表
    @GetMapping("/get")
    public Result<PageBean<Gwc>> get(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageBean<Gwc> pb=gwcService.get(pageNum,pageSize);
        return Result.success(pb);
    }

}
