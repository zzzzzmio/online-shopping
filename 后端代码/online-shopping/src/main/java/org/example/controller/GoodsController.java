package org.example.controller;

import org.example.pojo.Goods;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //添加商品
    @PostMapping("/add")
    public Result add(@RequestBody Goods g){
        Goods goods=goodsService.findGoods(g.getGoodsName());
        if(goods==null){
            goodsService.add(g);
            return Result.success();
        }
        else return Result.error("该商品已存在");
    }

    //修改商品信息
    @PutMapping("/update")
    public Result update(@RequestBody Goods g){
        Goods goods=goodsService.findOther(g.getId(),g.getGoodsName());
        if(goods==null){
            goodsService.update(g);
            return Result.success();
        }
        return Result.error("该商品已存在");
    }

    //获取商品信息
    @GetMapping("/get")
    public Result<Goods> get(@RequestParam Integer id){
        Goods goods=goodsService.get(id);
        return Result.success(goods);
    }

    //删除商品
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        goodsService.delete(id);
        return Result.success();
    }

    //客户获取商品列表
    @GetMapping("/getlist")
    public Result<PageBean<Goods>> getList(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(required = false) Integer categoryId){
        PageBean<Goods> pb=goodsService.getList(pageNum,pageSize,categoryId);
        return Result.success(pb);
    }
    //商家获取订单列表
    @GetMapping("/seller")
    public Result<PageBean<Goods>> seller(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(required = false) Integer categoryId){
        PageBean<Goods> pb=goodsService.seller(pageNum,pageSize,categoryId);
        return Result.success(pb);
    }
}
