package org.example.controller;

import org.example.pojo.Orders;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    //生成订单
    //前端传入一个orders类型的数组或者一个个传进来
    @PostMapping("/add")
    public Result add(@RequestBody List<Orders> list){
        for(int i=0;i<list.size();i++){
            Orders orders=list.get(i);
            orders.setState(1);
            ordersService.add(orders);
        }
        return Result.success();
    }
    //修改订单
    @PutMapping("/update")
    public Result update(@RequestBody Orders orders){
        ordersService.update(orders);
        return Result.success();
    }
    //删除订单
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        ordersService.delete(id);
        return Result.success();
    }
    //修改订单状态
    @PutMapping("/state")
    public Result state(@RequestParam Integer id,@RequestParam Integer state){
        ordersService.state(id,state);
        return Result.success();
    }
    //客户获取订单列表
    @GetMapping("/customer")
    public Result<PageBean<Orders>> customer(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageBean<Orders> pb=ordersService.customer(pageNum,pageSize);
        return Result.success(pb);
    }
    //商家获取订单列表
    @GetMapping("/seller")
    public Result<PageBean<Orders>> seller(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageBean<Orders> pb=ordersService.seller(pageNum,pageSize);
        return Result.success(pb);
    }
}
